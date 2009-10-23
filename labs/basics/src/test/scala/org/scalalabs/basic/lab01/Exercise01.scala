package org.scalalabs.basic.lab01

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import org.junit.Test

/*
 * Beginner Hello World:
 * 
 * Scala Objects
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 */
class Exercise01 extends JUnitSuite {

    @Test
    def shouldSayHello() {
        assert("Hello from Scala" === HelloWorld.sayHello)
    }

    @Test
    def shouldEcho() {
    	assert("Echo" === HelloWorld.echo("Echo"))
    }
}