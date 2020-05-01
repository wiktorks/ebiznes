package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class ProductRepository @Inject() (dbConfigProvider: DatabaseConfigProvider, categoryRepository: CategoryRepository, userRepository: UserRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  val prodRepo = TableQuery[ProductTable]
//  val catRepo = TableQuery[categoryRepository.CategoryTable]
//  val userRepo = TableQuery[userRepository.UserTable]

  class ProductTable(tag: Tag) extends Table[Product](tag, "product") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def description = column[String]("description")
    def category = column[Long]("category")
    def user = column[Long]("user")

    //def category_fk = foreignKey("category_fk", category, catRepo)(_.id)
    // user_fk = foreignKey("user_fk", user, userRepo)(_.id)

    def * = (id, name, description, category, user) <> ((Product.apply _).tupled, Product.unapply)
  }



  def create(name: String, description: String, category: Int, user: Int): Future[Product] = db.run {
    (prodRepo.map(p => (p.name, p.description,p.category,p.user))
      returning prodRepo.map(_.id)
      into {case ((name,description,category,user),id) => Product(id,name, description,category, user)}
      ) += (name, description,category, user)
  }

  def list(): Future[Seq[Product]] = db.run {
    prodRepo.result
  }

  def getById(id: Long): Future[Any] = db.run  {
    prodRepo.filter(_.id === id).result.head
  }
}
