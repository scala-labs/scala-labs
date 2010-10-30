package org.scalalabs.basic.lab01

/**
 * The goal of this exercise is to get familiar with the 'Trait' concept in Scala.
 *
 * Fix the code so that the unit test 'HelloWorldExerciseTest' passes.
 *
 * Information on traits can be found here in the O'Reilly book:
 * http://programming-scala.labs.oreilly.com/ch04.html 
 *
 */

object HelloWorldWithTraits extends HelloTrait with WorldTrait {

 /*
 * Hint:
 * - combine the 'helloMethod' of HelloTrait and the 'worldMethod' of WorldTrait to create a new message
 * - just replacing the FixMe string would of course be cheating :)
 */
	def hello:String = "FixMe"
}
