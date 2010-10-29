package org.scalalabs.basic.lab03

import Exercise07._
import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._

/**
 * @see Exercise07
 */

class Exercise07Test extends JUnitSuite {
  @Test
  def matchLanguageOnStrings() {
    assert("OOP" === Exercise07.describeLanguage("Java"))
    assert("OOP" === Exercise07.describeLanguage("Smalltalk"))
    assert("Functional" === Exercise07.describeLanguage("Clojure"))
    assert("Functional" === Exercise07.describeLanguage("Haskell"))
    assert("Hybrid" === Exercise07.describeLanguage("Scala"))
    assert("Procedural" === Exercise07.describeLanguage("C"))
    assert("Unknown" === Exercise07.describeLanguage("Oz"))
  }

  @Test
  def shouldMatchInputType = {
    assert("A string with length 8" === Exercise07.matchOnInputType("A String"))
    assert("A positive integer" === Exercise07.matchOnInputType(10))
    assert("A negative integer" === Exercise07.matchOnInputType(-1))
    assert("A Scala Option subtype" === Exercise07.matchOnInputType(Some(1)))
    assert("A Scala Option subtype" === Exercise07.matchOnInputType(None))
    assert("Some Scala class" === Exercise07.matchOnInputType(List(1, 2, 3)))
    assert("A null value" === Exercise07.matchOnInputType(null))
  }

  @Test
  def checkAge = {
    assert(Some("Jack") === Exercise07.older(new Person("Jack", 31)))
    assert(None === Exercise07.older(new Person("Jack", 30)))
  }

  @Test
  def removeConsecutiveDuplicates = {
    assert(List(1, 5, 8, 4, 9) === Exercise07.compress(List(1, 1, 1, 1, 5, 8, 8, 4, 4, 4, 9, 9)))
  }


  @Test
  def groupConsecutiveMembers = {
    assert(List(List(1, 1, 1), List(5), List(4, 4), List(1, 1)) === Exercise07.groupConsecutive(List(1, 1, 1, 5, 4, 4, 1, 1)))
  }

  @Test
  def groupEqualMembers = {
    assert(List(List(1, 1, 1, 1), List(5, 5), List(4, 4)) === Exercise07.groupEquals(List(1, 1, 1, 5, 4, 4, 5, 1)))
  }

  @Test
  def defineAmountEqualMembers = {
    assert(List((4, 'x), (2, 'y), (2, 'z)) === Exercise07.amountEqualMembers(List('x, 'x, 'x, 'y, 'z, 'z, 'y, 'x)))
    assert(List((4, "Cow"), (2, "Boy"), (1, "Hut")) === Exercise07.amountEqualMembers(List("Cow", "Cow", "Boy", "Cow", "Boy", "Hut", "Cow")))
  }

  @Test
  def zipMultiple = {
    assert(List(List(1, 'A, 'a), List(2, 'B, 'b), List(3,'C, 'c)) === Exercise07.zipMultiple(List(List(1,2,3), List('A, 'B ,'C), List('a, 'b, 'c))))
  }

  @Test
  def zipMultipleWithDifferentSize = {
    assert(List(List(1, 'A, 'a)) === Exercise07.zipMultipleWithDifferentSize(List(List(1,2), List('A, 'B , 'C), List('a))))
  }

  @Test
  def matchPartialFunctions = {
    //pf1 and pf2 are both partial functions.
    //These inherit from Scala's Function class, with an extra method: isDefinedAt
    //  pf3 should be defined in terms of pf1 and pf2

    assertTrue(Exercise07.pf1.isDefinedAt("scala-labs"))
    assertTrue(Exercise07.pf1.isDefinedAt("stuff"))
    assertFalse(Exercise07.pf1.isDefinedAt("other stuff"))
    assertTrue(Exercise07.pf2.isDefinedAt("other stuff"))


    assertTrue(Exercise07.pf3.isDefinedAt("scala-labs"))
    assertTrue(Exercise07.pf3.isDefinedAt("other stuff"))
  }
}