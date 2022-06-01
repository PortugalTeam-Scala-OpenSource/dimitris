import examples.Hello
import examples.Store.{BrowseProducts, Motherboard, ShowAllProducts, ShowProducts}

object Main extends App {

  val name = "Dimitris"
  val hello = Hello.sayHello(name)
  println(hello)

  println("--- Foreach Example ---")
  ShowAllProducts()
  println("")
  println("--- For Example ---")
  ShowProducts()
  println("")

  println("--- Zoo Example way ---")
  val browseProducts = new BrowseProducts()
  println(browseProducts.goNext)
  println(browseProducts.goNext)
  println(browseProducts.goNext)
  println(browseProducts.goNext)
  println(browseProducts.goNext)

}
