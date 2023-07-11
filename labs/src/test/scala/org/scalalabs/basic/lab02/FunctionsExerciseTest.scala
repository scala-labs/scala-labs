package org.scalalabs.basic.lab02

import org.scalalabs.basic.lab03.{FunctionsExercise01, FunctionsExercise02, FunctionsExercise03}
import org.specs2.mutable.Specification

/** @see
  *   FunctionsExercise1/2/3
  */
class FunctionsExerciseTest extends Specification {

  "FunctionsExercise01" should {
    "higher order function that does file resource handling while offering the content of the file as String" in {
      // uncomment function to make test pass
      FunctionsExercise01.doWithText(
        /*content => content.reverse*/
      ) ==== FunctionsExercise01.reverseText()
      FunctionsExercise01.doWithText(
        /*content => content.toUpperCase*/
      ) ==== FunctionsExercise01.upperCaseText()
    }
  }
  "FunctionsExercise02" should {
    "measure execution time" in {
      def block: Int = {
        Thread.sleep(10)
        4
      }
      // uncomment next line
      // 4 ==== FunctionsExercise02.measure(block)
      FunctionsExercise02.printed must beMatching(
        """The execution took: ([1-9][0-9]) ms"""
      )
    }
  }
  "FunctionsExercise03" should {
    "increment value with plusOne method" in {
      3 == FunctionsExercise03.plusOne(2)
      6 == FunctionsExercise03.plusOne(5)
    }
  }

}
