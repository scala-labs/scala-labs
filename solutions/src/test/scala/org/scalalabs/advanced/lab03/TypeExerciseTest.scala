package org.scalalabs.advanced.lab03

import org.specs2.mutable.Specification

class TypeExerciseTest extends Specification {
  "type exercise" should {
    "should build complete combomeal" in {
      import ComboMeal._

      //The following statements should not compile if the builder fully works:
      //val cm2:ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ build
      //val cm3: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ withSideOrder("AnotherPortion") ~ build

      //Only the following statement should
      val cm: ComboMealProduct =
        builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder(
          "Fries"
        ) ~ build
      success
    }

    "should build car" in {
      import ComposableBuilder._

      val car1 = new CarBuilder().build
      "brand: Toyota, color: Metallic, tire size: 15 Inch" ==== car1

      val car2 = new CarBuilder().withBrand("Mercedes").withColor("Green").build
      "brand: Mercedes, color: Green, tire size: 15 Inch" ==== car2

      val car3 = new CarBuilder()
        .withBrand("Mercedes")
        .withColor("Green")
        .withTireSize(17)
        .build
      "brand: Mercedes, color: Green, tire size: 17 Inch" ==== car3

    }

    "only mamals with same diet can share a_meal" in {
      import org.scalalabs.advanced.lab03.FoodExercise._
      val Cow = new Mamal { val eats = Grass }
      val Horse = new Mamal { val eats = Grass }
      val Shark = new Mamal { val eats = Fish }
      val jake = new Mamal { val eats = Pizza }
      val peet = new Mamal { val eats = Pizza }

      Cow.joinDinnerWith(Horse)
      jake.joinDinnerWith(peet)
      //doesn't compile!
      // Cow.joinDinnerWith(jake)
      success
    }

    "church natural numbers" in {
      import ChurchEncoding._
      type _1 = zero#succ
      type _2 = _1#succ
      Equals[_1, one]() ==== Equals()
      Equals[_2, two]() ==== Equals()

      Equals[two, one + one]() ==== Equals()
      Equals[two, one plus one]() ==== Equals()
      Equals[one, two - one]() ==== Equals()
    }

    "type safe registry" in {
      val tsStringReg = new TSRegistry[Int, String]

      tsStringReg.add(1, "Scala")
      tsStringReg.add(2, "Haskell")
      // Below doesn't compile
      // tsStringReg.add(3, 4)

      Some("Scala") === tsStringReg.safeGet[String](1)
      Some("Haskell") === tsStringReg.safeGet[String](2)
      None === tsStringReg.safeGet[String](3)

      //the following returns a None, since the get has been made typeSafe
      None === tsStringReg.safeGet[Int](1)
      None === tsStringReg.safeGet[Double](2)
    }

  }
}
