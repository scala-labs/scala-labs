package org.scalalabs.basic.lab03

import java.io.{IOException, InputStream}

import scala.io.Source
import scala.util.control._
import sys._

object OptionExercise {

  /** This map contains sample testdata to clarify this exercise. It contains
    * key value pairs where:
    *   - the key is a room number
    *   - the value can be: -- the amount of people in the room (filled:
    *     Some("10"), empty: None) -- the room is not available (Some("locked"))
    */
  val sampleRooms: Map[Int, Option[String]] = Map(
    1 -> Some("12"),
    2 -> None,
    3 -> Some("locked"),
    4 -> Some("14"),
    5 -> Some("8"),
    6 -> Some("locked")
  )

  /** Implement the room state method that should return the state of a room as
    * a String as follows:
    *   - filled: return total people: E.g: Some("12") is "12"
    *   - locked: return "not available" E.g. Some("locked") is "not available"
    *   - empty: return "empty" E.g. None is "empty"
    *   - does not exist: "not existing"
    */
  def roomState(rooms: Map[Int, Option[String]], room: Int): String = {
    error("Fix me")
  }

}

object EitherExercise {

  /** Implement the reciprocal method that should return the reciprocal of a
    * number. Every number has a reciprocal, defined by the formula 1/number,
    * except 0 (1/0 is undefined). Use Either to make it explicit that this
    * function can fail in case of:
    *   - unparseable input
    *   - 0 as an input
    *
    * Expected output for inputs:
    *   - Right(5) -> Right(0.2)
    *   - Right(0) -> Left(IllegalArgumentException("Reciprocal of 0 does not
    *     exist!"))
    *   - Left("2") -> Right(0.5)
    *   - Left("foo") -> Left(NumberFormatException)
    */
  def reciprocal(input: Either[String, Int]): Either[Throwable, Int] = {
    error("Fix me")
  }

}

object TryExercise {

  /** Rewrite the the method implementation of print(...) using {@code Try}
    * instead of try/catch. Make sure all tests keep succeeding.
    *
    * Hint: You can make use {@code Try}'s convenience methods such as recover,
    * flatMap, transform, foreach etc.
    */
  def print(inputStream: InputStream): Unit = {
    val readResult =
      try {
        Source.fromInputStream(inputStream).mkString
      } catch {
        case e: IOException => "Couldn't read input stream!"
      }
    val result =
      try {
        inputStream.close()
        readResult
      } catch {
        case throwable: Throwable => s"Error: Failed to close! $readResult"
      }
    println(result)
  }
}
