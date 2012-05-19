package org.scalalabs.basic.lab03
import sys._
/**
 * This excersice introduces you to Scala functions.
 *
 * Functions let you create control abstractions, which give extra opportunities to condense
 * and simplify code.
 *
 * Provide a suitable implementation in order to make the corresponding unittest work.
 *
 * Reference material to solve these exercises can be found here:
 * Functional programming in general: http://programming-scala.labs.oreilly.com/ch08.html
 * Partial Functions: http://programming-scala.labs.oreilly.com/ch08.html#PartialFunctions
 * Currying: http://programming-scala.labs.oreilly.com/ch08.html#Currying
 */
object BasicFunctionsExercise {

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