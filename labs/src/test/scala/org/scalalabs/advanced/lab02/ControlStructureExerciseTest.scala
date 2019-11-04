package org.scalalabs.advanced.lab02

import org.specs2.mutable.Specification

class ControlStructureExerciseTest extends Specification {

  val list: List[String] = List("aaa", "bbb", "cab", "def", "aab", "cba")
  val exercise = new ControlStructureExercise(list)

  /**
   * TODO
   * The exercises are commented out because otherwise nothing would compile
   * To start working on this exercise, comment them out and implement ControlStructureExercise
   */

  "control structure exercise" should {
    "test string filter" in {
      skipped("TODO: uncomment and fix")
      //      exercise.stringsContaining("c") ==== List("cab", "cba")
      //      exercise.stringsContaining("ab") ==== List("cab", "aab")
      //
      //      exercise.stringsEnding("b") ==== List("bbb", "cab", "aab")
      //      exercise.stringsEnding("c") ==== List()
    }

    "test curried string" in {
      skipped("TODO: uncomment and fix")
      //      exercise.helloConcat("Martin") ==== "Hello Martin"
      //      exercise.helloConcat("Lex") ==== "Hello Lex"
      //      exercise.goodByeConcat("Martin") ==== "Goodbye Martin"
      //      exercise.goodByeConcat("Bill") ==== "Goodbye Bill"
    }

  }
}