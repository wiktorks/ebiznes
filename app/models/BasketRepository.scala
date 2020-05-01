package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class BasketRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository, productRepository: ProductRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
//
//  val userRepo = TableQuery[userRepository.UserTable]
//  val productRepo = TableQuery[productRepository.ProductTable]
  val basketRepo = TableQuery[BasketTable]

  class BasketTable(tag: Tag) extends Table[Basket](tag, "basket") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def quantity = column[Int]("quantity")
    def user = column[Long]("user")
    def product = column[Long]("product")

//    def user_fk = foreignKey("user_fk", user, userRepo)
//    def product_fk = foreignKey("product_fk", product, productRepo)

    def * = (id, quantity, user, product) <> ((Basket.apply _).tupled, Basket.unapply)
  }

  def create(quantity: Int, user: Long, product: Long) : Future[Basket] = db.run {
    (basketRepo.map(p => (p.quantity, p.user,p.product))
      returning basketRepo.map(_.id)
      into {case ((quantity, user, product),id) => Basket(id,quantity, user, product)}
      ) += (quantity, user, product)
  }

  def list: Future[Any] = db.run {
    basketRepo.result
  }

  def getById(id: Long): Future[Any] = db.run  {
    basketRepo.filter(_.id === id).result.head
  }
}
