package org.scalalabs.basic.lab01

/**
 * The goal of this exercise is to get familiar with the idea behind Companion Objects
 *
 * Fix the code so that the unit test 'Exercise03Test' passes.
 *
 * More information on Companion Objects can be found here:
 * http://programming-scala.labs.oreilly.com/ch06.html#CompanionObjects
 *
 * Hints:
 * - A Class may have a Companion Object by the same name, which must be defined in the same source file
 * - The 'apply' method of an object is conventionally used as a factory method to create new instances of its
 *   companion class
 */
object HelloWorldClassAndObject {
	def apply(initialText:String):HelloWorldClassAndObject = {
		new HelloWorldClassAndObject {
			val text="FixMe"
		}
	}
}

abstract class HelloWorldClassAndObject {
	val text:String
	def echo:String = text
}