package org.scalalabs.basic.lab03

import org.specs2.mutable.Specification

/** @see
  *   ForExpressionExercise01
  */
class ForExpressionExerciseTest extends Specification {

  "ForExpressionExercise01" should {
    "find largest palindrom using a for expression" in {
      9009 === ForExpressionExercise01.largestPalindromWithForExpression(2)
      906609 === ForExpressionExercise01.largestPalindromWithForExpression(3)
    }
    "find largest palindrom using a higher order functions" in {
      9009 === ForExpressionExercise01.largestPalindromWithHigherOrderFunctions(
        2
      )
      906609 === ForExpressionExercise01
        .largestPalindromWithHigherOrderFunctions(3)
    }
  }

}
