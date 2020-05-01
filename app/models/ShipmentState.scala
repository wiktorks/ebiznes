package models

import play.api.libs.json.Json

case class ShipmentState(id: Long, status: String, shipment: Long)

object ShipmentState {
  implicit val ShipmentStateFormat = Json.format[ShipmentState]
}
