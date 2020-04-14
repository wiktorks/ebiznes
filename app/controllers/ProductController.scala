package controllers

import javax.inject._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ProductController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def createCategory: Action[AnyContent] = Action {
    Ok(<h1>Stworzono katerogię</h1>).as(HTML)
  }
  def getCategories: Action[AnyContent] = Action {
    Ok(<h1>Pobrano katerogie</h1>).as(HTML)
  }
  def changeCategory(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Zaktualizowano katerogię</h1>).as(HTML)
  }
  def deleteCategory(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Usunięto katerogię</h1>).as(HTML)
  }

  def createProduct: Action[AnyContent] = Action {
    Ok(<h1>Stworzono produkt</h1>).as(HTML)
  }
  def getProducts: Action[AnyContent] = Action {
    Ok(<h1>Pobrano produkty</h1>).as(HTML)
  }
  def changeProduct(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Zaktualizowano produkt</h1>).as(HTML)
  }
  def deleteProduct(id: Long): Action[AnyContent] = Action {
    Ok(<h1>Usunięto produkt</h1>).as(HTML)
  }

  def commitPayment: Action[AnyContent] = Action {
    Ok(<h1>Przesłano płatności</h1>).as(HTML)
  }
  def payment: Action[AnyContent] = Action {
    Ok(<h1>Stworzono płatność</h1>).as(HTML)
  }
  def discardPayment: Action[AnyContent] = Action {
    Ok(<h1>Wycofano płatność</h1>).as(HTML)
  }

  def addPromotion: Action[AnyContent] = Action {
    Ok(<h1>Dodano promocję</h1>).as(HTML)
  }
  def getPromotion: Action[AnyContent] = Action {
    Ok(<h1>Wyświetlono promocję</h1>).as(HTML)
  }
  def updatePromotion: Action[AnyContent] = Action {
    Ok(<h1>Zmieniono promocję</h1>).as(HTML)
  }
  def deletePromotion: Action[AnyContent] = Action {
    Ok(<h1>usunięto promocję</h1>).as(HTML)
  }
}
