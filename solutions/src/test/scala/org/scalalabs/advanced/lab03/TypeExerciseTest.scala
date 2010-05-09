package org.scalalabs.advanced.lab03

import org.junit.{Test}
import org.junit.Assert._

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
    val cm:ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ withSideOrder("Fries") ~ build
    
    //doesn't compile
//    val cm2:ComboMealProduct = builder ~ withBurger("BigMac") ~ withBeverage(Tall) ~ build
  }

  @Test
  def churchNaturalNumbers = {
    import ChurchEncoding._
    type _1 = zero#succ
    type _2 = _1#succ
    assertEquals(Equals[_1, one], Equals())
    assertEquals(Equals[_2, two], Equals())
  }
}

