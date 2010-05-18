package org.scalalabs.advanced.lab03

import org.joda.time.{LocalDate, DateTime}

/**
 * Created by IntelliJ IDEA.
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 1:52:50 PM
 * To change this template use File | Settings | File Templates.
 */
object ComboMeal {

sealed trait Size
case object Tall extends Size
case object Medium extends Size
case object Small extends Size

trait  Option[+X]
case class Some[+X](x:X) extends Option[X]{
  def get:X = x
}
trait None extends Option[Nothing]
case object None extends None

case class ComboMealProduct(val burger: String, val bev: Size, val sideOrder: String)


case class Builder[HAS_BURGER<:Option[String],HAS_BEVERAGE<:Option[Size], HAS_SIDEORDER<:Option[String]] private[ComboMeal]
    (burger:HAS_BURGER, bev: HAS_BEVERAGE, sideOrder: HAS_SIDEORDER) {
      def ~[X](f:Builder[HAS_BURGER,HAS_BEVERAGE,HAS_SIDEORDER] => X):X = f(this)
}

def withBurger[M<:Option[Size],D<:Option[String]](burger:String)(b:Builder[None,M,D]):Builder[Some[String],M,D] =
  Builder(Some(burger),b.bev,b.sideOrder)

def withBeverage[B<:Option[String],D<:Option[String]](bev:Size)(b:Builder[B,None,D]):Builder[B,Some[Size],D] =
  Builder(b.burger,Some(bev),b.sideOrder)

def withSideOrder[B<:Option[String],M<:Option[Size]](sideOrder:String)(b:Builder[B,M,None]):Builder[B,M,Some[String]] =
  Builder(b.burger, b.bev, Some(sideOrder))

def build(b:Builder[Some[String],Some[Size],Some[String]]):ComboMealProduct =
  ComboMealProduct(b.burger.get, b.bev.get, b.sideOrder.get)

def builder:Builder[None,None,None] = Builder(None,None,None)

}


object ComposableBuilder {
  trait Buildable[T] {
    def build: T
  }

  trait TireBuilder extends Buildable[String] {
    var size = 15

    def withTireSize(i: Int): this.type = {
      size = i;
      this
    }

    def build = "tire size: " + size + " Inch"
  }

  trait BasicCarBuilder extends Buildable[String] {
    var color = "Metallic"
    var brand = "Toyota"

    def withColor(c: String): this.type = {
      color = c
      this
    }

    def withBrand(b: String): this.type = {
      brand = b
      this
    }

    def build = "brand: " + brand + ", color: " + color
  }

  case class CarBuilder extends BasicCarBuilder with TireBuilder with Buildable[String] {
    override def build: String = List(super[BasicCarBuilder].build, super[TireBuilder].build).mkString(", ")
  }
}



object Combo {
  case class ComboMeal(val burger: String, val beverage: String, val sideOrder: String)
  trait Option[+X]
  case class Some[+X](x: X) extends Option[X] {
    def get: X = x
  }
  trait None extends Option[Nothing]
  case object None extends None

  case class Builder[HAS_BUR <: Option[String], HAS_BEV <: Option[String], HAS_SIDE <: Option[String]] private[Combo]
  (burger: HAS_BUR, bev: HAS_BEV, side: HAS_SIDE) {
    def ~[X](f: Builder[HAS_BUR, HAS_BEV, HAS_SIDE] => X): X = f(this)
  }
}



object FoodExercise {

  trait Food {def name: String}
  object Grass extends Food { def name="Grass" }
  object Beef extends Food {def name = "Beef"}
  object Fish extends Food {def name = "Fish"}
  object Pizza extends Food { def name="Beef" }
  
  trait Mamal { self =>
    val eats : Food
    def joinDinnerWith[T <: Mamal](other : T)(implicit sameFood: other.eats.type =:= self.eats.type) {}
    def prefers = "Eating " + eats.name
  }
}