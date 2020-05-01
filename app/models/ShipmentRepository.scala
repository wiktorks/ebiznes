package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class ShipmentRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository, productRepository: ProductRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

//  val userRepo = TableQuery[userRepository.UserTable]
  val shipmentRepo = TableQuery[ShipmentTable]

  class ShipmentTable(tag: Tag) extends Table[Shipment](tag, "shipment") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def address = column[String]("address")
    def user = column[Long]("user")

//    def user_fk = foreignKey("user_fk", user, userRepo)

    def * = (id, address, user) <> ((Shipment.apply _).tupled, Shipment.unapply)
  }

  def create(address: String, user: Long) : Future[Shipment] = db.run {
    (shipmentRepo.map(p => (p.address, p.user))
      returning shipmentRepo.map(_.id)
      into {case ((address, user),id) => Shipment(id,address, user)}
      ) += (address, user)
  }

  def list: Future[Any] = db.run {
    shipmentRepo.result
  }

  def getById(id: Long): Future[Any] = db.run  {
    shipmentRepo.filter(_.id === id).result.head
  }
}