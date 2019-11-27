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
   */
  def reciprocal(input: Either[String, Int]): Either[Throwable, Int] = {
    error("Fix me")
  }

}