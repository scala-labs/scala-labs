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


trait Buildable[T] {
  def build: T
}

trait HeadBuilder extends Buildable[String] {
  var eyeColor = "brown"
  var hairColor = "red"

  def withEyeColor(color: String): this.type = {
    eyeColor = color
    this
  }

  def withHairColor(color: String): this.type = {
    hairColor = color
    this
  }

  def build = "eyes: " + eyeColor + ", hair: " + hairColor
}

trait BodyBuilder extends Buildable[String] {
  var limbCount = 4

  def withNumLimbs(count: Int): this.type = {
    limbCount = count
    this
  }

  def build = "limbs: " + limbCount
}

class PersonBuilder extends HeadBuilder with BodyBuilder with Buildable[String] {
  override def build: String = List(super[BodyBuilder].build, super[HeadBuilder].build).mkString(", ")
}

//val person = new PersonBuilder().withEyeColor("blue").withNumLimbs(3).build
//println(person) // limbs: 3, eyes: blue, hair: red


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