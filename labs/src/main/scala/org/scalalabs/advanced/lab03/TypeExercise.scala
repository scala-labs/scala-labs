package org.scalalabs.advanced.lab03
import sys._

/** Created by IntelliJ IDEA. User: arjan Date: Apr 9, 2010 Time: 1:52:50 PM To
  * change this template use File | Settings | File Templates.
  */
object ComboMeal {
  sealed trait Size
  case object Tall extends Size
  case object Medium extends Size
  case object Small extends Size

  trait Option[+X]
  case class Some[+X](x: X) extends Option[X] {
    def get: X = x
  }
  trait None extends Option[Nothing]
  case object None extends None

  case class ComboMealProduct(burger: String, bev: Size, sideOrder: String)

  /** An example of a type safe builder. The builder will only build an object
    * once all attributes are set to a value, otherwise it won't compile! So, we
    * want to express something like the following: builder ~
    * withBurger("MyBurger") ~ withBeverage(Small) ~
    * withSideOrder("MySideOrder") ~ build while builder ~
    * withBurger("MyBurger") ~ build won't compile.
    *
    * For the case class were using the '<:' operator, which defines an upper
    * bound. T <: U means that T must be a subtype of U.
    */
  case class Builder[HAS_BURGER <: Option[String], HAS_BEVERAGE <: Option[
    Size
  ], HAS_SIDEORDER <: Option[String]] private[ComboMeal] (
    burger: HAS_BURGER,
    bev: HAS_BEVERAGE,
    sideOrder: HAS_SIDEORDER
  ) {
    def ~[X](f: Builder[HAS_BURGER, HAS_BEVERAGE, HAS_SIDEORDER] => X): X = f(
      this
    )
  }

  /** Implement this in a type-safe manner: this method <b>must</b> be called
    * once and only once for a given builder! If it is not called at all, or is
    * called twice, a compile error should be given.
    *
    * The latter is possible by using type constraints. For example: the
    * withBurger method takes a burger as first parameter, and a builder as the
    * second parameter. The Builder is typed for all three meal options (burger,
    * beverage and side order). So in this case, you should only be able to call
    * this method when the withBurger method has not already been called, in
    * which case the HAS_BURGER type should be the None object defined above.
    *
    * The method should return a new Builder, with the burger field now being
    * set to Some("string value"), and the beverage and sideOrder fields should
    * be taken over from the values that they already had.
    */
  def withBurger /*TODO insert type parameters here... */ (burger: String)(
    b: Builder[ /*TODO insert proper type parameters here*/ _, _, _]
  ): Builder[ /*TODO insert proper type parameters here*/ _, _, _] =
    error("Implement me")

  /** Implement similar as the above method
    */
  def withBeverage(bev: Size)(b: Builder[_, _, _]): Builder[_, _, _] = error(
    "implement me"
  )

  def withSideOrder(sideOrder: String)(b: Builder[_, _, _]): Builder[_, _, _] =
    error("implement me")

  /** The build method takes a builder as parameter. The build method may only
    * compile if called on a Builder, for which all three options (burger,
    * beverage, sideOrder) are filled, i.e. given a Some(value) value. Implement
    * this again by using type parameters
    */
  def build(
    b: Builder[ /*TODO insert type parameters here*/ _, _, _]
  ): ComboMealProduct = error("implement me")

  def builder: Builder[None, None, None] = Builder(None, None, None)

}

/** An example of a generic Buildable trait, with can be subclasses to build
  * specific parts of something that needs to be build.
  */

object ComposableBuilder {
  trait Buildable[T] {
    def build: T
  }

  trait TireBuilder extends Buildable[String] {
    var size = 15

    /** TODO implement this method such that it returns the actual type of the
      * actual implementation class. This ensure that we can call the
      * implementing build as follows: new
      * Builder().withSize(19).withOtherProperty("otherProp").with... etc
      */
    def withSize(i: Int): Nothing = error("fix me")

    def build: String = "tire size: " + size + " Inch"
  }
  trait BasicCarBuilder extends Buildable[String] {
    var color = "Metallic"
    var brand = "Toyota"

    /** TODO implement this method such that it returns the actual type of the
      * actual implementation class. This ensure that we can call the
      * implementing build as follows: new
      * Builder().withSize(19).withOtherProperty("otherProp").with... etc
      */
    def withColor(c: String): this.type = error("fix me")

    /** TODO implement this method such that it returns the actual type of the
      * actual implementation class. This ensure that we can call the
      * implementing build as follows: new
      * Builder().withSize(19).withOtherProperty("otherProp").with... etc
      */
    def withBrand(b: String): this.type = error("fix me")

    def build: String = "brand: " + brand + ", color: " + color
  }

  class CarBuilder
      extends BasicCarBuilder
      with TireBuilder
      with Buildable[String] {
    override def build: String =
      "" // TODO implement the build method by using the implementation traits
  }
}

object Combo {
  case class ComboMeal(burger: String, beverage: String, sideOrder: String)
  trait Option[+X]
  case class Some[+X](x: X) extends Option[X] {
    def get: X = x
  }
  trait None extends Option[Nothing]
  case object None extends None

  case class Builder[HAS_BUR <: Option[String], HAS_BEV <: Option[
    String
  ], HAS_SIDE <: Option[String]] private[Combo] (
    burger: HAS_BUR,
    bev: HAS_BEV,
    side: HAS_SIDE
  ) {
    def ~[X](f: Builder[HAS_BUR, HAS_BEV, HAS_SIDE] => X): X = f(this)
  }
}

object FoodExercise {

  /** Types of food that are commonly eaten.
    */
  trait Food { def name: String }
  object Grass extends Food { def name = "Grass" }
  object Beef extends Food { def name = "Beef" }
  object Fish extends Food { def name = "Fish" }
  object Pizza extends Food { def name = "Beef" }

  /** The Mammal trait should be implemented so that the joinDinnerWith method
    * only compiles if two mammals eat exactly the same type of foo.d
    */
  trait Mammal { self =>
    val eats: Food

    /** TODO implement this is such a way using clever type parameters and
      * implicits such that a Mammal can only join dinner with another mammal
      * that eats exactly the same type of Food, i.e. Mammal1.eats.type ==
      * Mammal2.eats.type the method should be callable as follows:
      * mammal1.joinDinnerWith(mammal2)
      */
    def joinDinnerWith(
      other: Any // TODO Any is not the right type here, add type parameters, and possibly some clever implicits.
    ): Unit = ??? // TODO implement me

    def prefers: String = "Eating " + eats.name
  }
}
