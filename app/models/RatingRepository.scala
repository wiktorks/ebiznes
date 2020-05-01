package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class RatingRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository, productRepository: ProductRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

//  val userRepo = TableQuery[userRepository.UserTable]
//  val productRepo = TableQuery[productRepository.ProductTable]
  val ratingRepo = TableQuery[RatingTable]

  class RatingTable(tag: Tag) extends Table[Rating](tag, "rating") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def mark = column[Int]("mark")
    def comment = column[String]("comment")
    def user = column[Long]("user")
    def product = column[Long]("product")

//    def user_fk = foreignKey("user_fk", user, userRepo)
//    def product_fk = foreignKey("product_fk", product, productRepo)

    def * = (id, mark, comment, user, product) <> ((Rating.apply _).tupled, Rating.unapply)
  }

  def create(mark: Int, comment: String, user: Long, product: Long) : Future[Rating] = db.run {
    (ratingRepo.map(p => (p.mark, p.comment,p.user,p.product))
      returning ratingRepo.map(_.id)
      into {case ((mark,comment,user,product),id) => Rating(id,mark,comment,user,product)}
      ) += (mark,comment,user,product)
  }

  def list: Future[Any] = db.run {
    ratingRepo.result
  }

  def getById(id: Long): Future[Any] = db.run  {
    ratingRepo.filter(_.id === id).result.head
  }
}
