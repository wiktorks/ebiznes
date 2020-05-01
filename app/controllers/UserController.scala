package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc){
  def registerUser: Action[AnyContent]  = Action {
    Ok(<h1>Zarejestrowano użytkownika</h1>).as(HTML)
  }
  def loginUser: Action[AnyContent]  = Action {
    Ok(<h1>zalogowano użytkownika</h1>).as(HTML)
  }
  def getUser(id: Long): Action[AnyContent]  = Action {
    Ok(<h1>Pobrano użytkownika</h1>).as(HTML)
  }
  def updateUser(id: Long): Action[AnyContent]  = Action {
    Ok(<h1>Zaktualizowano użytkownika</h1>).as(HTML)
  }
  def deleteUser(id: Long): Action[AnyContent]  = Action {
    Ok(<h1>Usunięto użytkownika</h1>).as(HTML)
  }

  def postOpinion: Action[AnyContent] = Action {
    Ok(<h1>Dodano opinię</h1>).as(HTML)
  }
  def getOpinions: Action[AnyContent] = Action {
    Ok(<h1>Pobrano opinie</h1>).as(HTML)
  }
  def updateOpinion(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Zaktualizowano opinię</h1>).as(HTML)
  }
  def deleteOpinion(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Usunięto opinię</h1>).as(HTML)
  }

  def getBaset(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Wyświetlono koszyk</h1>).as(HTML)
  }
  def updateBasket(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Dodano do koszyka</h1>).as(HTML)
  }
  def deleteFromBasket(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Usunięto z koszyka</h1>).as(HTML)
  }
  def emptyBasket(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Wyczyszczono koszyk</h1>).as(HTML)
  }

  def getWishList(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Wyświetlono listę życzeń</h1>).as(HTML)
  }
  def createWishList(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Stworzono listę życzeń</h1>).as(HTML)
  }
  def addToWishList(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Dodano do listy życzeń</h1>).as(HTML)
  }
  def deleteWishList(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Usunięto z listy życzeń</h1>).as(HTML)
  }

  def createShipment(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Stworzono wysyłkę</h1>).as(HTML)
  }
  def getShipment(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Wyświetlono wysyłkę</h1>).as(HTML)
  }
  def addToShipment(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Dodano produkt do wysyłki</h1>).as(HTML)
  }
  def deleteShipment(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Usunięto wysyłkę</h1>).as(HTML)
  }

  def addScore(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Dodano ocenę</h1>).as(HTML)
  }
  def getScore(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Wyświetlono ocenę</h1>).as(HTML)
  }
  def updateScore(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Zmieniono ocenę</h1>).as(HTML)
  }
  def removeScore(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Usunięto ocenę</h1>).as(HTML)
  }
}
