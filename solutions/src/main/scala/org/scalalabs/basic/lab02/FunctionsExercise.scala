package org.scalalabs.basic.lab02

import java.util.Scanner

import scala.language.reflectiveCalls

/**
 * Higher order functions allow you to build abstractions containing a generic control
 * structure and a function with which the result(s) of the generic control structure can
 * be used in different ways.
 *
 * Take a look at the predefined methods reverseText() and upperCaseTest().
 * Both methods contain a lot of duplication which we want to remove.
 *
 * Implement the doWithText() method as a higher order function
 * that takes care of the resource handling of the File and offers a function argument
 * that allows to deal with the content of the File, which is a String, directly.
 */
object FunctionsExercise01 {

  def doWithText(handleFun: String => String): String = {
    val scanner = new Scanner(getClass.getResourceAsStream("/text.txt"))
    try {
      scanner.useDelimiter("\\Z");
      val content = scanner.next()
      handleFun(content)
    } finally scanner.close()
  }

  def reverseText(): String = {
    val scanner = new Scanner(getClass.getResourceAsStream("/text.txt"))
    try {
      scanner.useDelimiter("\\Z");
      val content = scanner.next()
      content.reverse
    } finally scanner.close()
  }

  def upperCaseText(): String = {
    val scanner = new Scanner(getClass.getResourceAsStream("/text.txt"))
    try {
      scanner.useDelimiter("\\Z");
      val content = scanner.next()
      content.toUpperCase()
    } finally scanner.close()
  }

}

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
object FunctionsExercise02 {

  var printed: String = _
  private def logPerf(elapsed: Long) = printed =
    s"The execution took: $elapsed ms"

  def measure[T](block: => T): T = {
    val started = System.currentTimeMillis
    val res = block
    logPerf(System.currentTimeMillis - started)
    res
  }

}

/**
 * Functions let you create control abstractions, which give extra opportunities to condense
 * and simplify code.
 *
 * Provide a suitable implementation in order to make the corresponding unittest work.
 */
object FunctionsExercise03 {
  def plusOne(x: Int): Int = {
    //implement this using a partial function
    val partial = plus(1, _: Int)
    partial(x)
  }

  def plus(x: Int, y: Int): Int = x + y

  def using[A <: { def close(): Unit }, B](closable: A)(f: A => B): B =
    try {
      f(closable)
    } finally {
      closable.close()
    }
}
