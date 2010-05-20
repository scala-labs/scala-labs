package org.scalalabs.advanced.lab02

import org.junit.Test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._

/**
 * Created by IntelliJ IDEA.
 * User: lieke
 * Date: Apr 9, 2010
 */

class ControlStructureExerciseTest {


  @Test
  def testStringFilter {
    val list : List[String] = List("aaa", "bbb", "cab", "def", "aab", "cba")
    val exercise = new ControlStructureExercise(list)
    assertEquals(exercise.stringsContaining("c"), List("cab", "cba"))
    assertEquals(exercise.stringsContaining("ab"), List("cab", "aab"))

    assertEquals(exercise.stringsEnding("b"), List("bbb", "cab", "aab"))
    assertEquals(exercise.stringsEnding("c"), List())
  }


}