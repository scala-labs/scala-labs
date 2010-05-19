package org.scalalabs.advanced.lab03

import org.junit.{Test}
import org.junit.Assert._
import org.scalalabs.advanced.lab03.ManifestSample.TSReg


/**
 * Created by IntelliJ IDEA.
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 2:43:15 PM
 * To change this template use File | Settings | File Templates.
 */
class TypeExerciseTest {
  @Test
  def shouldBuildCompleteCombomeal = {
    import ComboMeal._


    //The following statements should not compile if the builder fully works:
    //val cm2:ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ build
    //val cm3: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ withSideOrder("AnotherPortion") ~ build

    //Only the following statement should
    val cm: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ build
  }

  @Test
  def shouldBuildCar = {
    import ComposableBuilder._

    val car1 = new CarBuilder().build
    assertEquals("brand: Toyota, color: Metallic, tire size: 15 Inch", car1)

    val car2 = new CarBuilder().withBrand("Mercedes").withColor("Green").build
    assertEquals("brand: Mercedes, color: Green, tire size: 15 Inch", car2)

    val car3 = new CarBuilder().withBrand("Mercedes").withColor("Green").withTireSize(17).build
    assertEquals("brand: Mercedes, color: Green, tire size: 17 Inch", car3)

  }

  @Test
  def onlyMamalsWithSameDietCanShareAMeal {
    import org.scalalabs.advanced.lab03.FoodExercise._
    val Cow = new Mamal { val eats = Grass }
    val Horse = new Mamal { val eats = Grass }
    val Shark = new Mamal { val eats = Fish }
    val jake = new Mamal { val eats = Pizza }
    val peet = new Mamal { val eats = Pizza }

    Cow.joinDinnerWith(Horse)
    jake.joinDinnerWith(peet)
    //doesn't compile!
    //Cow.joinDinnerWith(jake)
  }

  @Test
  def churchNaturalNumbers = {
    import ChurchEncoding._
    type _1 = zero#succ
    type _2 = _1#succ
    assertEquals(Equals[_1, one], Equals())
    assertEquals(Equals[_2, two], Equals())

    assertEquals(Equals[two, one + one], Equals())
    assertEquals(Equals[two, one plus one], Equals())
    assertEquals(Equals[one, two - one], Equals())
  }

  @Test
  def typeSafeRegistry = {
    val tsReg = new TSReg[Int, String]

    tsReg.add(1, "Scala")
    tsReg.add(2, "Haskell")

    assertEquals(Some("Scala"), tsReg.safeGet[String](1))
    assertEquals(Some("Haskell"), tsReg.safeGet[String](2))
    assertEquals(None, tsReg.safeGet[String](3))

    //the following returns a None, since the get has been made typeSafe
    assertEquals(None, tsReg.safeGet[Int](1))
  }


}

