package org.scalalabs.basic.lab03

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, IOException, InputStream}

import org.junit.runner.RunWith
import org.scalalabs.basic.lab03.TryExercise01._
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import scala.util.control.NoStackTrace

/**
 * @see OptionExercise
 */
@RunWith(classOf[JUnitRunner])
class TryExerciseTest extends Specification with Mockito {

  "TryExercise01" should {
    "correctly print contents of input stream to STDOUT" in {
      val input =
        """
          |Hello
          |World!
          |""".stripMargin

      val out = new ByteArrayOutputStream
      Console.withOut(out) {
        print(new ByteArrayInputStream(input.getBytes))
      }
      out.toString.trim ===
        """Hello
          |World!""".stripMargin
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