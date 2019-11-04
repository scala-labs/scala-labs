package org.scalalabs.basic.lab03

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import RecursionPatternMatchingExercise._
/**
 * @see RecursionPatternMatchingExercise
 */
@RunWith(classOf[JUnitRunner])
class RecursionPatternMatchingExerciseTest extends Specification {

  "RecursionPatternMatchingExercise" should {
    "check subsequent values increase" in {
      true === checkValuesIncrease(List(1, 2, 3, 5, 10))
      true === checkValuesIncrease(List(1))
      false === checkValuesIncrease(List(1, 2, 2, 5, 10))
      false === checkValuesIncrease(List(1, 2, 2, 5, 1))
    }
    "group consecutive members" in {
      List(List(1, 1, 1), List(5), List(4, 4), List(1, 1)) === groupConsecutive(List(1, 1, 1, 5, 4, 4, 1, 1))
    }
    "group equal members" in {
      List(List(1, 1, 1, 1), List(5, 5), List(4, 4)) === groupEquals(List(1, 1, 1, 5, 4, 4, 5, 1))
    }
    "remove consecutive duplicates" in {
      List(1, 5, 8, 4, 9) === compress(List(1, 1, 1, 1, 5, 8, 8, 4, 4, 4, 9, 9))
    }
    "define amount equal members" in {
      List((4, "x"), (2, "y"), (2, "z")) === amountEqualMembers(List("x", "x", "x", "y", "z", "z", "y", "x"))
      List((4, "Cow"), (2, "Boy"), (1, "Hut")) === amountEqualMembers(List("Cow", "Cow", "Boy", "Cow", "Boy", "Hut", "Cow"))
    }
    "zip multiple" in {
      List(List(1, "A", "a"), List(2, "B", "b"), List(3, "C", "c")) === zipMultiple(List(List(1, 2, 3), List("A", "B", "C"), List("a", "b", "c")))
    }
    "zip multiple with different size" in {
      List(List(1, "A", "a")) === zipMultipleWithDifferentSize(List(List(1, 2), List("A", "B", "C"), List("a")))
    }
  }
}