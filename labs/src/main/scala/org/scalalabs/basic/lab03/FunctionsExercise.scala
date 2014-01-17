package org.scalalabs.basic.lab03
import scala.language.reflectiveCalls
import sys._
/**
 * This exercise introduces you to Scala functions.
 *
 * Functions let you separate responsibilities, which allow you to maximally reuse code.
 *
 * Create a method measure that accepts any code blocks, executes it and prints the execution time.
 * E.g. 'The execution took <elapsed> ms'.
 * Use the logPerf method provided.
 * Provide a suitable implementation in order to make the corresponding unittest work.
 */
object FunctionsExercise01 {

  var printed = "" 
  private def logPerf(elapsed: Long) = printed = s"The execution took: $elapsed ms"

  def measure[T](/* provide correct method parameter */): T = {
    error("fix me")
  }

}

/**
 * Functions let you create control abstractions, which give extra opportunities to condense
 * and simplify code.
 *
 * Provide a suitable implementation in order to make the corresponding unittest work.
 */
object FunctionsExercise02 {

  def plusOne(x: Int): Int = {
    //implement this using a partial function
    error("fix me")
  }

  def plus(x: Int, y: Int): Int = {
    x + y
  }

  def using[A <: { def close(): Unit }, B](closable: A)(f: A => B): B = {
    error("fix me")
  }
}
