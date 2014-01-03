package org.scalalabs.basic.lab03

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
 
/**
 * @see BasicForExpressionExercise01
 */
@RunWith(classOf[JUnitRunner])
class BasicForExpressionExerciseTest extends Specification {

  "BasicForExpressionExercise01" should {
    "find largest palindrom using a for expression" in {
      9009 === BasicForExpressionExercise01.largestPalindromWithForExpression(2)
      906609 === BasicForExpressionExercise01.largestPalindromWithForExpression(3)
    }
    "find largest palindrom using a higher order functions" in {
      9009 === BasicForExpressionExercise01.largestPalindromWithHigherOrderFunctions(2)
      906609 === BasicForExpressionExercise01.largestPalindromWithHigherOrderFunctions(3)
    }
  }

}