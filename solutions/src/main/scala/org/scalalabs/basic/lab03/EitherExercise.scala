package org.scalalabs.basic.lab03

import scala.util.control._
import sys._

object EitherExercise01 {

  /**
   * Implement the reciprocal method that should return the reciprocal of a number. Every number has
   * a reciprocal, defined by the formula 1/number, except 0 (1/0 is undefined).
   * Use Either to make it explicit that this function can fail in case of:
   * - unparseable input
   * - 0 as an input
   *
   * Expected output for inputs:
   * - Right(5) -> Right(0.2)
   * - Right(0) -> Left(IllegalArgumentException("Reciprocal of 0 does not exist!"))
   * - Left("2") -> Right(0.5)
   * - Left("foo") -> Left(NumberFormatException)
   */
  def reciprocal(input: Either[String, Int]): Either[Throwable, Double] = {

    (input match {
      case Left(str) =>
        Exception.catching(classOf[NumberFormatException]).either(str.toInt)
      case Right(i) if i == 0 =>
        Left(new IllegalArgumentException("Reciprocal of 0 does not exist!"))

      case Right(i) => Right(i)
    }).map(1.0 / _)

    // better
    input.fold(
      str =>
        Exception.catching(classOf[NumberFormatException]).either(str.toInt), // try parse to an int
      i => Right(i))
      .filterOrElse(i => i != 0, new IllegalArgumentException("Reciprocal of 0 does not exist!"))
      .map(1.0 / _)

  }

}