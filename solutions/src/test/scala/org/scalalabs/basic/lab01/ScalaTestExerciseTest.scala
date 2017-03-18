package org.scalalabs.basic.lab01

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit._
/**
 * In this Lab you will implement a ScalaTest testcase.
 *
 * Instructions:
 * 1. Implement the divide method in Euro that has the following signature:  def /(divider:Int) = ???
 * - If the divider is <=0 throw an IllegalArgumentException
 *
 * 2. Write a ScalaTest using a Spec of your choice to test:
 * - Happy flow (divider is > 0)
 * - Alternative flow (divider is <= 0)
 */
@RunWith(classOf[JUnitRunner])
class ScalaTestExerciseTest extends FunSpecLike with Matchers {
  
  describe("Euro") {
    it("should be divisible") {
      val result = new Euro(1, 20) / 5
      result.euro should be(0)
      result.cents should be(24)
    }

    it("must produce an IllegalArgumentException if divided with <= 0") {
      intercept[IllegalArgumentException] {
        new Euro(1, 2) / 0
      }
    }

  }

}
