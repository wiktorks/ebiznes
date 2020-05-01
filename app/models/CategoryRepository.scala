package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class CategoryRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CategoryTable(tag: Tag) extends Table[Category](tag, "category") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")

    def * = (id, name) <> ((Category.apply _).tupled, Category.unapply)
  }

  val category = TableQuery[CategoryTable]

  def create(name: String): Future[User] = db.run {
    (category.map(p => (p.name))
      returning category.map(_.id)
      into {case ((name), id) => User(id, name)}
      ) += (name)
  }

  def list(): Future[Any] = db.run {
    category.result
  }

  def getByName(name: String): Future[Any] = db.run {
    category.filter(_.name === name).result
  }


  def getById(id: Long): Future[Any] = db.run {
    category.filter(_.id === id).result.head
  }
}
