package org.scalalabs.basic.lab03

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._

/**
 * @see RecursionPatternMatchingExercise
 */
class RecursionPatternMatchingExerciseTest extends JUnitSuite {

  @Test
  def removeConsecutiveDuplicates = {
    assert(List(1, 5, 8, 4, 9) === RecursionPatternMatchingExercise.compress(List(1, 1, 1, 1, 5, 8, 8, 4, 4, 4, 9, 9)))
  }


  @Test
  def groupConsecutiveMembers = {
    assert(List(List(1, 1, 1), List(5), List(4, 4), List(1, 1)) === RecursionPatternMatchingExercise.groupConsecutive(List(1, 1, 1, 5, 4, 4, 1, 1)))
  }

  @Test
  def groupEqualMembers = {
    assert(List(List(1, 1, 1, 1), List(5, 5), List(4, 4)) === RecursionPatternMatchingExercise.groupEquals(List(1, 1, 1, 5, 4, 4, 5, 1)))
  }

  @Test
  def defineAmountEqualMembers = {
    assert(List((4, 'x), (2, 'y), (2, 'z)) === RecursionPatternMatchingExercise.amountEqualMembers(List('x, 'x, 'x, 'y, 'z, 'z, 'y, 'x)))
    assert(List((4, "Cow"), (2, "Boy"), (1, "Hut")) === RecursionPatternMatchingExercise.amountEqualMembers(List("Cow", "Cow", "Boy", "Cow", "Boy", "Hut", "Cow")))
  }

  @Test
  def zipMultiple = {
    assert(List(List(1, 'A, 'a), List(2, 'B, 'b), List(3, 'C, 'c)) === RecursionPatternMatchingExercise.zipMultiple(List(List(1, 2, 3), List('A, 'B, 'C), List('a, 'b, 'c))))
  }

  @Test
  def zipMultipleWithDifferentSize = {
    assert(List(List(1, 'A, 'a)) === RecursionPatternMatchingExercise.zipMultipleWithDifferentSize(List(List(1, 2), List('A, 'B, 'C), List('a))))
  }
}