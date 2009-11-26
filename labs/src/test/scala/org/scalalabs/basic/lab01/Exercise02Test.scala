package org.scalalabs.basic.lab01

import _root_.org.scalatest.junit.JUnitSuite
import org.junit.Test


/*
 * Beginner Hello World:
 * 
 * Scala inheritence with Traits
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 *
 * See HelloWorldWithTraits.scala for help and hints.
 */

class Exercise02Test extends JUnitSuite {
	@Test
  def testHelloFromTraits() {
	  assert ( "Hello World" === HelloWorldWithTraits.hello)
	}
}
