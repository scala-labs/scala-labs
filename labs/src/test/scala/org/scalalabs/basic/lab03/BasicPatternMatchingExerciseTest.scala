package org.scalalabs.basic.lab03

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