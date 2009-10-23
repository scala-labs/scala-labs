package com.xebia.scala

// root import because our own package is named "scala", which overrides the toplevel scala package
import _root_.scala.xml._

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