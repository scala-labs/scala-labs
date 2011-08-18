package org.scalalabs.advanced.lab03

import org.junit.{ Test }
import org.junit.Assert._

/**
 * See @BuilderExercise
 */

class BuilderExerciseTest {

  @Test
  def shouldOnlyBuildCompleteCombomeal = {
    import ComboMealBuilder._

    val cm1: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ build
    assertEquals(ComboMealProduct("BigMac", Tall, "Fries"), cm1)

    val cm2: ComboMealProduct = builder ~ withSideOrder("Fries") ~ withBurger("BigMac") ~ withBeverage(Tall) ~ build
    assertEquals(cm1, cm2)

    // TODO: the following shouldn't compile, if the builder is correctly implemented
    // val cm3: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ build
    // val cm4: ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ build
  }

  @Test
  def shouldBuildCar = {
    import ComposableBuilder._

    val car1 = new CarBuilder().build
    assertEquals("brand: Toyota, color: Metallic, tire size: 15 Inch", car1)

    // TODO uncomment and let the tests run afer implementation of our builder
    // val car2 = new CarBuilder().withBrand("Mercedes").withColor("Green").build
    // assertEquals("brand: Mercedes, color: Green, tire size: 15 Inch", car2)
    // 
    // val car3 = new CarBuilder().withBrand("Mercedes").withColor("Green").withTireSize(17).build
    // assertEquals("brand: Mercedes, color: Green, tire size: 17 Inch", car3)
  }

}
