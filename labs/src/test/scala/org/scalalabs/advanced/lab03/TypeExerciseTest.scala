package org.scalalabs.advanced.lab03


import org.junit.{Test}
import org.junit.Assert._
import org.scalalabs.advanced.lab03.ManifestSample.TSReg
import org.scalalabs.advanced.lab03.ChurchEncoding.{CFalse, CTrue}

/**
 * Created by IntelliJ IDEA.
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 2:43:15 PM
 * To change this template use File | Settings | File Templates.
 */


class TypeExerciseTest {
  @Test
  def shouldBuildCar = {
    import ComposableBuilder._

    val car1 = new CarBuilder().build
    assertEquals("brand: Toyota, color: Metallic, tire size: 15 Inch", car1)

    //TODO uncomment and let the tests run afer implementation of our builder
//    val car2 = new CarBuilder().withBrand("Mercedes").withColor("Green").build
//    assertEquals("brand: Mercedes, color: Green, tire size: 15 Inch", car2)
//
//    val car3 = new CarBuilder().withBrand("Mercedes").withColor("Green").withTireSize(17).build
//    assertEquals("brand: Mercedes, color: Green, tire size: 17 Inch", car3)
  }

  @Test
  def shouldOnlyBuildCompleteCombomeal = {
    import ComboMeal._
    val cm: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ build

    //the following don't compile, if the builder is correctly implemented
    //    val cm2:ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ build
    //    val cm3:ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ build
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
    def churchBooleanTypes = {
      //TODO implement the Church Boolean data types so that the following line compiles:
//      val ctrue: CTrue#cond[Int, String] = 10
      //TODO and the following line should not compile:
//      val ctrue2: CTrue#cond[Int, String] = "10"
//      error: type mismatch;
//                found   : java.lang.String("10")
//                required: Int
//       val ctrue2: CTrue#cond[Int,String] = "10"

      //TODO and the following line should again compile:
//      val cfalse: CFalse#cond[Int,String] = "10"

    }


  @Test
  def churchNaturalNumbers = {

    //TODO define the Church numerals using Scala traits/types/classes or whatever you can think of.
    // Then uncomment the lines below so that the following compiles:
//    import ChurchEncoding._
//
//    assertEquals(Equals[two, one + one], Equals())
//    assertEquals(Equals[two, one plus one], Equals())
//    assertEquals(Equals[one, two - one], Equals())
//
//    type _1 = zero#succ
//    type _2 = _1#succ
//    assertEquals(Equals[_1, one], Equals())
//    assertEquals(Equals[_2, two], Equals())
  }

}