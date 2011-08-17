package org.scalalabs.advanced.lab02

import org.junit.Test
import org.junit.Assert._

/**
 * Created by IntelliJ IDEA.
 * User: lieke
 * Date: Apr 9, 2010
 */

class ControlStructureExerciseTest {

  val list: List[String] = List("aaa", "bbb", "cab", "def", "aab", "cba")
  val exercise = new ControlStructureExercise(list)

  /**
   * TODO
   * The exercises are commented out because otherwise nothing would compile
   * To start working on this exercise, comment them out and implement ControlStructureExercise
   */

  @Test
  def testStringFilter {
    /*
    assertEquals(exercise.stringsContaining("c"), List("cab", "cba"))
    assertEquals(exercise.stringsContaining("ab"), List("cab", "aab"))

    assertEquals(exercise.stringsEnding("b"), List("bbb", "cab", "aab"))
    assertEquals(exercise.stringsEnding("c"), List())
    */
  }

  @Test
  def testCurriedString {
    /*
    assertEquals(exercise.helloConcat("Martin"), "Hello Martin")
    assertEquals(exercise.helloConcat("Lex"), "Hello Lex")

    assertEquals(exercise.goodByeConcat("Martin"), "Goodbye Martin")
    assertEquals(exercise.goodByeConcat("Bill"), "Goodbye Bill")
   */
  }
}