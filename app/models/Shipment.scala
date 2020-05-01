package models

import play.api.libs.json.Json

case class Shipment(id: Long, address: String, user: Long)

object Shipment {
  implicit val ShipmentFormat = Json.format[Shipment]
}
