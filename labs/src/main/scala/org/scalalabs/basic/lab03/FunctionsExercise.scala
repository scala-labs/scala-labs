package org.scalalabs.basic.lab03

import java.io.File
import java.net.URL
import java.util.Scanner

import scala.language.reflectiveCalls
import sys._

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

  def doWithText( /* provide correct function signature */ ): String = {
    error("fix me")
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
 * Functions let you separate responsibilities, which allow you to maximally reuse code.
 *
 * Create a method measure that accepts any code blocks, executes it and prints the execution time.
 * E.g. 'The execution took <elapsed> ms'.
 * Use the logPerf method provided.
 * Provide a suitable implementation in order to make the corresponding unittest work.
 */
object FunctionsExercise02 {

  var printed = ""

  private def logPerf(elapsed: Long) = printed = s"The execution took: $elapsed ms"

  def measure[T]( /* provide correct method parameter */ ): T = {
    error("fix me")
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
    //implement this by using the plus method with a partially applied construct
    error("fix me")
  }

  def plus(x: Int, y: Int): Int = {
    x + y
  }

}
