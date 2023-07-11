package org.scalalabs.basic.lab01

import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.should.Matchers

/** In this Lab you will implement a ScalaTest testcase.
  *
  * Instructions:
  *   1. Implement the divide method in Euro that has the following signature:
  *      def /(divider:Int) = ???
  *   - If the divider is <=0 throw an IllegalArgumentException
  *
  * 2. Write a ScalaTest using a Spec of your choice to test:
  *   - Happy flow (divider is > 0)
  *   - Alternative flow (divider is <= 0)
  */

class ScalaTestExerciseTest extends AnyFunSpecLike with Matchers {
  describe("Euro") {
    it("should be divisible") {
      fail("Implement me")
    }

    it("must produce an IllegalArgumentException if divided <= 0") {
      fail("Implement me")
    }
  }
}
