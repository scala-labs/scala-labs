package org.scalalabs.basic.lab01

/**
 * The goal of this exercise is to get familiar with the basic language constructs of Scala.
 *
 * Fix the code so that the unit test 'HelloWorldExerciseTest' passes.
 *
 */
object HelloWorld {

  /**
   * This defines the 'sayHello' field of the HelloWorld object.
   *
   * Field declarations in Scala are public by default. There is no need to create a getter to access this value, as is
   * common in Java, HelloWorld.sayHello will have the same effect.
   *
   * Alternative notations:
   * val sayHello = "FixMe"                          -> Type annotation is optional, will be automatically inferred
   * val sayHello: String = {"FixMe"}                 -> Brackets are also optional
   *
   * Declaring a value with val will create an immutable field. An immutable field in Scala is the same as a final field
   * in Java. A mutable field can be created by declaring the field with var:
   * var sayHello: String = "FixMe"
   *
   * More on variable declarations can be found here:
   * http://programming-scala.labs.oreilly.com/ch02.html#VariableDeclarationsAndDefinitions
   */
	val sayHello = "Hello from Scala"


  /**
   * This defines the 'echo' method of the HelloWorld object.
   *
   * Like in Java, the method arguments (text:String) are between round brackets. Unlike Java, the return type of
   * the method (:String) is again at the end of the method signature, just before the method body.
   *
   * Alternative notations:
   * def echo(text: String): String = {"FixMe"}        -> Brackets around the method body are optional
   * def echo(text) = "FixMe"                         -> Type annotation is optional
   *
   * Example of a void method:
   * def print(text) { println(text) }               -> note the absence of '=' before the method body
   * def print(text): Unit = { println(text) }       -> a void method can also have as a return value Unit 
   *
   * More on method declarations can be found here:
   * http://programming-scala.labs.oreilly.com/ch02.html#MethodDeclarationsAndDefinitions
   */
	def echo(text: String): String = text
}


/*================================= Objects =====================================*/
/**
 * The goal of this exercise is to get familiar with the idea behind Companion Objects
 *
 * Fix the code so that the unit test 'HelloWorldExerciseTest' passes.
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
			val text=initialText
		}
	}
}

abstract class HelloWorldClassAndObject {
	val text:String
	def echo:String = text
}

/*================================= Traits =====================================*/

object HelloWorldWithTraits extends HelloTrait with WorldTrait {

 /**
 * Hint:
 * - combine the 'helloMethod' of HelloTrait and the 'worldMethod' of WorldTrait to create a new message
 * - just replacing the FixMe string would of course be cheating :)
 */
	def hello:String = helloMethod + " " + worldMethod
}

trait HelloTrait {
	def helloMethod:String = "Hello"
}

trait WorldTrait {
	def worldMethod:String = "World"
}



