package org.scalalabs.basic.lab03

import org.scalalabs.basic.lab03.Exercise07
import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._
/**
 * Fix the tests by implementing some functions given in Exercise06
 *
 * @author Arjan
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
     assert("Some Scala class" === Exercise07.matchOnInputType(List(1,2,3)))
     assert("A null value" === Exercise07.matchOnInputType(null))
  }
}