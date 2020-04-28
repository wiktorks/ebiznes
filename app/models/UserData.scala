package models

import play.api.libs.json.Json

case class UserData(id: Long, email: String, surname: String, phone: Int, country: String, city: String, street: String, user: Long)

object UserData {
  implicit val UserDataFormat = Json.format[UserData]
}
