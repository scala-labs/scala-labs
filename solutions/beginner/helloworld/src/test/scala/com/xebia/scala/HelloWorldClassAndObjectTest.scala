package com.xebia.scala

// root import because our own package is named "scala", which overrides the toplevel scala package
import _root_.scala.xml._

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

/*
 * Beginner Hello World:
 * 
 * Scala Classes and companion Objects
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 * 
 * Hint: 
 * - A Class may have a companion Ojbect by the same name, defined
 *   in the same source file
 * - An Object can be constructed using an apply method
 */

class HelloWorldClassAndObjectTest extends JUnit3Suite {
    def testInstanceIsCreatedByCompanion() { 
      val helloWorldInstance = HelloWorldClassAndObject("Hello")
      assert("Hello" === helloWorldInstance.echo)
    }
}
