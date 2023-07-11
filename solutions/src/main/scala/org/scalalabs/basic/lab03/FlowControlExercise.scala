package org.scalalabs.basic.lab03

import java.io.{IOException, InputStream}

import scala.io.Source
import scala.util.{Success, Try}
import scala.util.control.Exception

object OptionExercise {

  /** Implement the room state method that should return the state of a room as
    * a String as follows:
    *   - filled: Some("12") -> 12
    *   - empty: None -> "empty"
    *   - locked: Some("locked") -> "not available"
    *   - does not exist: "not existing"
    */
  def roomState(rooms: Map[Int, Option[String]], room: Int): String = {
    rooms
      .get(room)
      .map { roomState =>
        roomState
          .map { value =>
            if (value == "locked") "not available"
            else value
          }
          .getOrElse("empty")
      }
      .getOrElse("not existing")
    // better
    rooms
      .getOrElse(room, Some("not existing"))
      .map(roomState =>
        if (roomState == "locked") "not available" else roomState
      )
      .getOrElse("empty")

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
  def reciprocal(input: Either[String, Int]): Either[Throwable, Double] = {

    (input match {
      case Left(str) =>
        Exception.catching(classOf[NumberFormatException]).either(str.toInt)
      case Right(i) if i == 0 =>
        Left(new IllegalArgumentException("Reciprocal of 0 does not exist!"))

      case Right(i) => Right(i)
    }).map(1.0 / _)

    // better
    input
      .fold(
        str =>
          Exception
            .catching(classOf[NumberFormatException])
            .either(str.toInt), // try parse to an int
        i => Right(i)
      )
      .filterOrElse(
        i => i != 0,
        new IllegalArgumentException("Reciprocal of 0 does not exist!")
      )
      .map(1.0 / _)

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
    Try(Source.fromInputStream(inputStream).mkString)
      .recover { case e: IOException =>
        "Couldn't read input stream!"
      }
      .flatMap { str =>
        Try(inputStream.close())
          .transform(
            _ => Success(str),
            _ => Success(s"Error: Failed to close! $str")
          )
      }
      .foreach(output => println(output))
  }

}
