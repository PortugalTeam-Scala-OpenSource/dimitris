package examples

import scala.util.Try

object Store {
  trait Product {
    val name: String
    val price: Double
    val isAvailable: Boolean
  }
  case class Motherboard(name: String,price: Double,isAvailable:Boolean) extends Product
  case class Processor(name: String,price: Double,isAvailable:Boolean) extends Product
  case class GPU(name: String,price: Double,isAvailable:Boolean) extends Product

  val products = Seq(
    Motherboard("Mobo1",100.23,true),
    Motherboard("Mobo2",50.65,false),
    Processor("Mobo3",200.93,true),
  )

  def ShowAllProducts( ) : Unit = {
    products.foreach { println }
  }

  def ShowProducts( ) : Unit = {
    for (product <- products) {
      println(product.name)
    }
  }

  object BrowseProducts {
    case class NoMoreProductsToBrowse()
    case class NextProduct(product: Product)
  }
  class BrowseProducts(param:Any) {
    import BrowseProducts._
    val steps: Iterator[Product] = products.iterator

    def goNext: Either[NoMoreProductsToBrowse, NextProduct] = Try {
      steps.next()
    }.toEither match {
      case Left(exception) => Left(NoMoreProductsToBrowse())
      case Right(product)   => Right(NextProduct(product))
    }

  }
}
