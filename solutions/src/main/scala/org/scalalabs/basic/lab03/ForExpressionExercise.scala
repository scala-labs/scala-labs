package org.scalalabs.basic.lab03

import sys._
/**
 * This exercise introduces you to Scala's for expression.
 *
 * For expressions are similar to for loops but are much more powerful.
 *
 * In this exercise we will solve problem number 4 of the Euler project preferably using
 * a for expression. In order to see how higher order functions are related to a for expression
 * you will also provide a solution with higher order functions.
 *
 * The problem definition is as follows:
 *
 * A palindromic number reads the same both ways.
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 x 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 *
 * <a href="http://projecteuler.net/problem=4">Project Euler - Problem 4</a>
 *
 */
object ForExpressionExercise01 {

  /**
   * Helper method to calculate lowest and highest number based on
   * the amount of digits provided. The lowest and highest number (from and to)
   * is returned as a Tuple.
   * E.g. amountOfDigits = 2 -> from = 10, to = 99
   */
  private def getFromAndTo(amountOfDigits: Int): (Int, Int) = {
    require(amountOfDigits > 1, "amount of digits must be at least 2")
    import Math.pow
    val fromNumber = pow(10, amountOfDigits - 1).toInt
    val toNumber = pow(10, amountOfDigits).toInt - 1
    (fromNumber, toNumber)
  }

  /**
   * Calculate the largest palindrome from a n-digit number using a for expression.
   *
   * E.g. the largest palindrome of a 2-digit number is:
   * 91 * 99 == 9009, where 9009 is a palindrome
   * @param amountOfDigits amount of digits from which to calculate the largest palindrome
   * @return largest palindrome.
   */
  def largestPalindromWithForExpression(amountOfDigits: Int): Int = {
    val (fromNumber, toNumber) = getFromAndTo(amountOfDigits)
    val res = for {
      i ← fromNumber to toNumber
      j ← i to toNumber
      prod = i * j
      if prod.toString == prod.toString.reverse
    } yield prod
    res.max
  }

  /**
   * Calculate the largest palindrome from a n-digit number using higher order functions.
   *
   * E.g. the largest palindrome of a 2-digit number is:
   * 91 * 99 == 9009, where 9009 is a palindrome
   * @param amountOfDigits amount of digits from which to calculate the largest palindrome
   * @return largest palindrome.
   */
  def largestPalindromWithHigherOrderFunctions(amountOfDigits: Int): Int = {
    val (fromNumber, toNumber) = getFromAndTo(amountOfDigits)
    (fromNumber to toNumber).flatMap(i ⇒ i to toNumber map (j ⇒ i * j))
      .filter(prod ⇒ prod.toString == prod.toString.reverse)
      .max
  }
}
