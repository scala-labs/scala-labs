package org.scalalabs.basic.lab03

import org.scalatest.junit.JUnitSuite
//import org.scalalabs.basic.lab03.Person
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