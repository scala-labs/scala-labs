package com.xebia.scala

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

class HelloWorldTraitTest extends JUnit3Suite {
	def testHelloFromTraits() { 
	  assert ( "Hello World" === HelloWorldWithTraits.hello)
	}
}