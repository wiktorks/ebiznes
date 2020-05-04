package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class PaymentsRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository, basketRepository: BasketRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  val paymentsRepo = TableQuery[PaymentsTable]

  class PaymentsTable(tag: Tag) extends Table[Payments](tag, "payments") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def amount = column[Float]("amount")
    def user = column[Long]("user")
    def basket = column[Long]("basket")


    def * = (id, amount, user, basket) <> ((Payments.apply _).tupled, Payments.unapply)
  }

  def create(amount: Float, user: Long, basket: Long) : Future[Payments] = db.run {
    (paymentsRepo.map(p => (p.amount, p.user,p.basket))
      returning paymentsRepo.map(_.id)
      into {case ((amount, user, basket),id) => Payments(id,amount, user, basket)}
      ) += (amount, user, basket)
  }

  def list: Future[Any] = db.run {
    paymentsRepo.result
  }

  def getById(id: Long): Future[Any] = db.run  {
    paymentsRepo.filter(_.id === id).result.head
  }
}
