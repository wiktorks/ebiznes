package models

import play.api.libs.json.Json

case class Basket(id: Long, quantity: Int, user: Long, product: Long)

object Basket {
  implicit val BasketFormat = Json.format[Basket]
}
