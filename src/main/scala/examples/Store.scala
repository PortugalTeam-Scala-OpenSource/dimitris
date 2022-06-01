package examples

import scala.reflect.ClassTag
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

  class BrowseProducts[TypeOfProduct<: Product : ClassTag]() {
    import BrowseProducts._

    val temp: Seq[TypeOfProduct] = products.collect {
      case p:TypeOfProduct => p
    }

    val steps: Iterator[TypeOfProduct] = temp.iterator


    val motherboard: Seq[Product] = products.filter {
      case GPU(name, price, isAvailable) => false
      case Motherboard(name, price, isAvailable) => true
      case Processor(name, price, isAvailable) => false
      case _ => false
    }

    val processor: Seq[Processor] = products.collect {
      case p:Processor => p
    }

    def goNext: Either[NoMoreProductsToBrowse, NextProduct] = Try {
      steps.next()
    }.toEither match {
      case Left(exception) => Left(NoMoreProductsToBrowse())
      case Right(product)   => Right(NextProduct(product))
    }

  }
}
