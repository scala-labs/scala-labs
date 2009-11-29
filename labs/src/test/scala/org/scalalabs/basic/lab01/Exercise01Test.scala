package org.scalalabs.basic.lab01

import _root_.org.scalatest.junit.JUnitSuite
import org.junit.Test

/*
 * Beginner Hello World:
 * 
 * Scala Objects
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 *
 * See HelloWorld.scala for help and hints.s
 */
class Exercise01Test extends JUnitSuite {

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
}