package org.scalalabs.basic.lab01

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.junit.Test


/*
 * Beginner Hello World:
 * 
 * Scala inheritence with Traits
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 * 
 * Hint: 
 * - combine the methods in HelloTrait and Worldtrait to 
 *   create a new message
 */

class Exercise02Test extends JUnitSuite {
	@Test def testHelloFromTraits() {
	  assert ( "Hello World" === HelloWorldWithTraits.hello)
	}
}
