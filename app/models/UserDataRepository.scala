package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class UserDataRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

//  val userRepo = TableQuery[userRepository.UserTable]
  val userDataRepo = TableQuery[UserDataTable]

  class UserDataTable(tag: Tag) extends Table[UserData](tag, "userData") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("name")
    def surname = column[String]("surname")
    def phone = column[Int]("phone")
    def country = column[String]("country")
    def city = column[String]("city")
    def street = column[String]("street")
    def user = column[Long]("user_fk")

//    def user_fk = foreignKey("user_fk", user, userRepo)

    def * = (id, email, surname, phone, country, city, street, user) <> ((UserData.apply _).tupled, UserData.unapply)
  }

  def create(email: String, surname: String, phone: Int, country: String, city: String, street: String, user: Long): Future[UserData] = db.run {
    (userDataRepo.map(p => (p.email, p.surname,p.phone,p.country,p.city,p.street,p.user))
      returning userDataRepo.map(_.id)
      into {case ((email,surname,phone,country,city,street,user),id) => UserData(id,email,surname,phone,country,city,street,user)}
      ) += (email,surname,phone,country,city,street,user)
  }

  def list(): Future[Any] = db.run {
    userDataRepo.result
  }

  def getById(id: Long): Future[Any] = db.run  {
    userDataRepo.filter(_.id === id).result.head
  }
}
