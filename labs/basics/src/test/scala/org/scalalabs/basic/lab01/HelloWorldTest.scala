package com.xebia.scala

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

/*
 * Beginner Hello World:
 * 
 * Scala Objects
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 */
class HelloWorldTest extends JUnit3Suite {

    def testSayHello() {
        assert("Hello from Scala" === HelloWorld.sayHello)
    }
    
    def testEcho() {
    	assert("Echo" === HelloWorld.echo("Echo"))
    }
}