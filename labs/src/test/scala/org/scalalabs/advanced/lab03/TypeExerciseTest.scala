package org.scalalabs.advanced.lab03

import org.scalalabs.advanced.lab03.ManifestSample.TSReg
import org.specs2.mutable.Specification

class TypeExerciseTest extends Specification {
  "type exercise" should {
    "should build complete combomeal" in {
      import ComboMeal._

      //The following statements should not compile if the builder fully works:
      //val cm2:ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ build
      //val cm3: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ withSideOrder("AnotherPortion") ~ build

      skipped("TODO uncomment and let the tests run afer implementation of our builder")
      //Only the following statement should
      //      val cm: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ build
      //      success
    }

    "should build car" in {
      skipped("TODO uncomment and fix")
      import ComposableBuilder._

      val car1 = new CarBuilder().build
      "brand: Toyota, color: Metallic, tire size: 15 Inch" ==== car1

      //      val car2 = new CarBuilder().withBrand("Mercedes").withColor("Green").build
      //      "brand: Mercedes, color: Green, tire size: 15 Inch" ==== car2
      //
      //      val car3 = new CarBuilder().withBrand("Mercedes").withColor("Green").withTireSize(17).build
      //      "brand: Mercedes, color: Green, tire size: 17 Inch" ==== car3

    }

    "only mamals with same diet can share a_meal" in {

      import org.scalalabs.advanced.lab03.FoodExercise._
      skipped("TODO uncomment and fix")
      //      val Cow = new Mamal { val eats = Grass }
      //      val Horse = new Mamal { val eats = Grass }
      //      val Shark = new Mamal { val eats = Fish }
      //      val jake = new Mamal { val eats = Pizza }
      //      val peet = new Mamal { val eats = Pizza }
      //
      //      Cow.joinDinnerWith(Horse)
      //      jake.joinDinnerWith(peet)
      //      //doesn't compile!
      //      //Cow.joinDinnerWith(jake)
      //      success

    }

    "church natural numbers" in {
      skipped("TODO define the Church numerals using Scala traits/types/classes or whatever you can think of. " +
        "Then uncomment the lines below so that the following compiles:")
      //      import ChurchEncoding._
      //      type _1 = zero#succ
      //      type _2 = _1#succ
      //      Equals[_1, one] ==== Equals()
      //      Equals[_2, two] ==== Equals()
      //
      //      Equals[two, one + one] ==== Equals()
      //      Equals[two, one plus one] ==== Equals()
      //      Equals[one, two - one] ==== Equals()
    }

    "type safe registry" in {
      skipped("TODO uncomment and fix")
      //      val tsReg = new TSReg[Int, String]
      //
      //      tsReg.add(1, "Scala")
      //      tsReg.add(2, "Haskell")
      //
      //      Some("Scala") === tsReg.safeGet[String](1)
      //      Some("Haskell") === tsReg.safeGet[String](2)
      //      None === tsReg.safeGet[String](3)
      //
      //      //the following returns a None, since the get has been made typeSafe
      //      None === tsReg.safeGet[Int](1)
    }

  }
}

