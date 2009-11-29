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
 *   companion class. This allows you to leave off the "new" keyword when constructing instances, as you can
 *   see in the unit test.
 *
 * The factory method below creates an anonymous subclass of the HelloWorldClassAndObject class and overrides
 * the "text" field. A more traditional solution would be to pass the initial text as a parameter to a
 * constructor. The class would then look like this:
 *
 * class HelloWorldClassAndObject(val text:String) {
 *   def echo:String = text
 * }
 *
 * and the apply method in the companion object could then just call that constructor.
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