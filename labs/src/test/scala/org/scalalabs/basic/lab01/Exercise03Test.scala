package org.scalalabs.basic.lab01

import org.scalatest._
import junit.{JUnitSuite}
import org.junit.Test

/*
 * Beginner Hello World:
 * 
 * Scala Classes and Companion Objects
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 *
 * See HelloWorldClassAndObject.scala for help and hints.
 * 
 */

class Exercise03Test extends JUnitSuite {
    @Test def testInstanceIsCreatedByCompanion() {
      val helloWorldInstance = HelloWorldClassAndObject("Hello")
      assert("Hello" === helloWorldInstance.echo)
    }
}
