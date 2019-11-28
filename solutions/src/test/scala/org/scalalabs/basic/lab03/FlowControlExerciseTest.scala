package org.scalalabs.basic.lab03

import java.io.{ ByteArrayInputStream, ByteArrayOutputStream, IOException, InputStream }

import org.junit.runner.RunWith
import org.scalalabs.basic.lab03.EitherExercise._
import org.scalalabs.basic.lab03.OptionExercise.roomState
import org.scalalabs.basic.lab03.TryExercise01.print
import org.specs2.matcher.EitherMatchers
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import scala.util.control.NoStackTrace

/**
 * @see FlowControlExercise
 */
@RunWith(classOf[JUnitRunner])
class FlowControlExerciseTest extends Specification with EitherMatchers with Mockito {

  "OptionExercise" should {
    val rooms = Map(1 -> Some("12"), 2 -> None, 3 -> Some("locked"), 4 -> Some("14"), 5 -> Some("8"), 6 -> Some("locked"))

    "correctly show the state of filled room (e.g. Some(12))" in {
      roomState(rooms, 1) === "12"
    }
    "correctly show the state of an empty room (None)" in {
      roomState(rooms, 2) === "empty"
    }
    "correctly show the state of a room that is not available (Some(locked))" in {
      roomState(rooms, 3) === "not available"
    }
    "correctly show the state of a room that does not exist (no entry in Map)" in {
      roomState(rooms, 100) === "not existing"
    }
  }

  "EitherExercise" should {
    "correctly calculate reciprocal of integer" in {
      reciprocal(Right(5)) must beRight(0.2)
      reciprocal(Right(-2)) must beRight(-0.5)
    }

    "correctly calculate reciprocal of integer encoded as string" in {
      reciprocal(Left("10")) must beRight(0.1)
      reciprocal(Left("-4")) must beRight(-0.25)
    }

    "correctly encapsulate error on inputting 0 value" in {
      reciprocal(Right(0)).left.map(_.getMessage) must beLeft("Reciprocal of 0 does not exist!")
    }

    "correctly calculate reciprocal of unparseable string" in {
      reciprocal(Left("foo")).left.map(_.getMessage) must beLeft("For input string: \"foo\"")
      reciprocal(Left("bar")).left.map(_.getMessage) must beLeft("For input string: \"bar\"")
    }

  }

  "TryExercise01" should {
    "correctly print contents of input stream to STDOUT" in {
      val input = """Hello World!"""

      val out = new ByteArrayOutputStream
      Console.withOut(out) {
        print(new ByteArrayInputStream(input.getBytes))
      }
      out.toString.trim === """Hello World!"""
    }

    "correctly print error when failed to read stream" in {
      val out = new ByteArrayOutputStream
      Console.withOut(out) {
        print(mock[InputStream])
      }
      out.toString.trim === "Couldn't read input stream!"
    }

    "correctly print content and error when closing stream" in {
      val in = mock[InputStream]

      in.close() throws new IOException("BOOOM!") with NoStackTrace

      val out = new ByteArrayOutputStream
      Console.withOut(out) {
        print(in)
      }
      out.toString.trim === "Error: Failed to close! Couldn't read input stream!"
    }
  }

}