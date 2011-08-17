package org.scalalabs.basic.lab01

/**
 * The goal of this exercise is to get familiar with the basic language constructs of Scala.
 *
 * Fix the code so that the unit test 'HelloWorldExerciseTest' passes.
 *
 */
object HelloWorld {

  /*
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
  val sayHello: String = "FixMe"

  /*
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
  def echo(text: String): String = "FixMe"
}
