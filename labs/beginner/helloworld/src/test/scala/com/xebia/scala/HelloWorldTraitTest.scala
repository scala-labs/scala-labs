package com.xebia.scala

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

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

class HelloWorldTraitTest extends JUnit3Suite {
	def testHelloFromTraits() {
	  assert ( "Hello World" === HelloWorldWithTraits.hello)
	}
}
