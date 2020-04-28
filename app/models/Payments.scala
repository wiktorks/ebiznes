package models

import play.api.libs.json.Json

case class Payments(id: Long, amount: Float, user: Long, basket: Long)

object Payments {
  implicit val PaymentsFormat = Json.format[Payments]
}
