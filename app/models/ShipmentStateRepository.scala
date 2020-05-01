package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class ShipmentStateRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, shipmentRepository: ShipmentRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

//  val shipmentRepo = TableQuery[shipmentRepository.ShipmentTable]
  val shipmentStateRepo = TableQuery[ShipmentStateTable]

  class ShipmentStateTable(tag: Tag) extends Table[ShipmentState](tag, "shipmentState") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def status = column[String]("status")
    def shipment = column[Long]("shipment")

//    def shipment_fk = foreignKey("shipment_fk", shipment, shipmentRepo)

    def * = (id, status, shipment) <> ((ShipmentState.apply _).tupled, ShipmentState.unapply)
  }

  def create(id: Long, status: String, shipment: Long) : Future[ShipmentState] = db.run {
    (shipmentStateRepo.map(p => (p.status, p.shipment))
      returning shipmentStateRepo.map(_.id)
      into {case ((status, shipment),id) => ShipmentState(id,status, shipment)}
      ) += (status, shipment)
  }

  def list: Future[Any] = db.run {
    shipmentStateRepo.result
  }

  def getById(id: Long): Future[Any] = db.run  {
    shipmentStateRepo.filter(_.id === id).result.head
  }
}