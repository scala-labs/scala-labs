package org.scalalabs.basic.lab01

import org.scalatest.junit.JUnitSuite
import org.junit.Test

/**
 * Beginner Hello World:
 * Scala Objects
 * Scala inheritence with Traits
 * Scala Classes and companion Objects
 */
class HelloWorldExerciseTest extends JUnitSuite {

  /*
  * Scala Objects
  *
  * Your job is to implement the objects and classes in
  * such a way that the tests in this suite all succeed.
  */

  @Test
  def shouldSayHello() {
    // The === operator used below is not an operator at all but a method in the
    // JUnitSuite super class, which is part of the ScalaTest library. it behaves
    // as a traditional assertEquals but produces very clear assertion errors when
    // values don't match. In Scala, methods can be used as if they were operators.
    assert("Hello from Scala" === HelloWorld.sayHello)
  }

  @Test
  def shouldEcho() {
    assert("Echo" === HelloWorld.echo("Echo"))
  }

  /*==============================================================*/

  /*
  * Scala inheritence with Traits
  *
  * Your job is to implement the objects and classes in
  * such a way that the tests in this suite all succeed.
  *
  * Hint:
  * - combine the methods in HelloTrait and Worldtrait to
  *   create a new message
  */

  @Test
  def testHelloFromTraits() {
    assert("Hello World" === HelloWorldWithTraits.hello)
  }

  /*==============================================================*/

  /*
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

  @Test
  def testInstanceIsCreatedByCompanion() {
    val helloWorldInstance = HelloWorldClassAndObject("Hello")
    assert("Hello" === helloWorldInstance.echo)
  }

}