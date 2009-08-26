package com.xebia.scala
// root import because our own package is named "scala", which overrides the toplevel scala package
import _root_.scala.xml._

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

/*
 * uh-oh, hoe zorgen we ervoor dat iemand een Trait gebruikt?
 */
class HelloWorldTraitTest extends JUnit3Suite {
	def testDummy() { fail }
}
