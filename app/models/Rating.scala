package models

import play.api.libs.json.Json

case class Rating(id: Long, mark: Int, comment: String, user: Long, product: Long)

object Rating {
  implicit val RatingFormat = Json.format[User]
}
