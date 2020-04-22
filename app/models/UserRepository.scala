package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class UserRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class UserTable(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")

    def * = (id, name) <> ((User.apply _).tupled, User.unapply)
  }

  private val user = TableQuery[User]

  def create(name: String): Future[User] = db.run {
    (user.map(p => (p.name))
      returning user.map(_.id)
      into {case ((name), id) => User(id, name)}
      ) += (name)
  }

  def list(): Future[Any] = db.run {
    user.result
  }

  def getByName(name: String): Future[Any] = db.run {
    user.filter(_.name == name)(implicitly).result
  }


  def getById(id: Long): Future[Any] = db.run {
    user.filter(_.id == id)(implicitly).result.head
  }
}
