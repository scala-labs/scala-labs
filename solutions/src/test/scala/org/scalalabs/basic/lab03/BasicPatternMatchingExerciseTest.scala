package org.scalalabs.basic.lab03

import BasicPatternMatchingExercise._
import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._

/**
 * @see BasicPatternMatchingExercise
 */

class BasicPatternMatchingExerciseTest extends JUnitSuite {
  @Test
  def matchLanguageOnStrings() {
    assert("OOP" === BasicPatternMatchingExercise.describeLanguage("Java"))
    assert("OOP" === BasicPatternMatchingExercise.describeLanguage("Smalltalk"))
    assert("Functional" === BasicPatternMatchingExercise.describeLanguage("Clojure"))
    assert("Functional" === BasicPatternMatchingExercise.describeLanguage("Haskell"))
    assert("Hybrid" === BasicPatternMatchingExercise.describeLanguage("Scala"))
    assert("Procedural" === BasicPatternMatchingExercise.describeLanguage("C"))
    assert("Unknown" === BasicPatternMatchingExercise.describeLanguage("Oz"))
  }

  @Test
  def shouldMatchInputType = {
    assert("A string with length 8" === BasicPatternMatchingExercise.matchOnInputType("A String"))
    assert("A positive integer" === BasicPatternMatchingExercise.matchOnInputType(10))
    assert("A negative integer" === BasicPatternMatchingExercise.matchOnInputType(-1))
    assert("A Scala Option subtype" === BasicPatternMatchingExercise.matchOnInputType(Some(1)))
    assert("A Scala Option subtype" === BasicPatternMatchingExercise.matchOnInputType(None))
    assert("Some Scala class" === BasicPatternMatchingExercise.matchOnInputType(List(1, 2, 3)))
    assert("A null value" === BasicPatternMatchingExercise.matchOnInputType(null))
  }

  @Test
  def checkAge = {
    assert(Some("Jack") === BasicPatternMatchingExercise.older(new Person("Jack", 31)))
    assert(None === BasicPatternMatchingExercise.older(new Person("Jack", 30)))
  }

  @Test
  def removeConsecutiveDuplicates = {
    assert(List(1, 5, 8, 4, 9) === BasicPatternMatchingExercise.compress(List(1, 1, 1, 1, 5, 8, 8, 4, 4, 4, 9, 9)))
  }


  @Test
  def groupConsecutiveMembers = {
    assert(List(List(1, 1, 1), List(5), List(4, 4), List(1, 1)) === BasicPatternMatchingExercise.groupConsecutive(List(1, 1, 1, 5, 4, 4, 1, 1)))
  }

  @Test
  def groupEqualMembers = {
    assert(List(List(1, 1, 1, 1), List(5, 5), List(4, 4)) === BasicPatternMatchingExercise.groupEquals(List(1, 1, 1, 5, 4, 4, 5, 1)))
  }

  @Test
  def defineAmountEqualMembers = {
    assert(List((4, 'x), (2, 'y), (2, 'z)) === BasicPatternMatchingExercise.amountEqualMembers(List('x, 'x, 'x, 'y, 'z, 'z, 'y, 'x)))
    assert(List((4, "Cow"), (2, "Boy"), (1, "Hut")) === BasicPatternMatchingExercise.amountEqualMembers(List("Cow", "Cow", "Boy", "Cow", "Boy", "Hut", "Cow")))
  }

  @Test
  def zipMultiple = {
    assert(List(List(1, 'A, 'a), List(2, 'B, 'b), List(3,'C, 'c)) === BasicPatternMatchingExercise.zipMultiple(List(List(1,2,3), List('A, 'B ,'C), List('a, 'b, 'c))))
  }

  @Test
  def zipMultipleWithDifferentSize = {
    assert(List(List(1, 'A, 'a)) === BasicPatternMatchingExercise.zipMultipleWithDifferentSize(List(List(1,2), List('A, 'B , 'C), List('a))))
  }

  @Test
  def matchPartialFunctions = {
    //pf1 and pf2 are both partial functions.
    //These inherit from Scala's Function class, with an extra method: isDefinedAt
    //  pf3 should be defined in terms of pf1 and pf2

    assertTrue(BasicPatternMatchingExercise.pf1.isDefinedAt("scala-labs"))
    assertTrue(BasicPatternMatchingExercise.pf1.isDefinedAt("stuff"))
    assertFalse(BasicPatternMatchingExercise.pf1.isDefinedAt("other stuff"))
    assertTrue(BasicPatternMatchingExercise.pf2.isDefinedAt("other stuff"))


    assertTrue(BasicPatternMatchingExercise.pf3.isDefinedAt("scala-labs"))
    assertTrue(BasicPatternMatchingExercise.pf3.isDefinedAt("other stuff"))
  }
}