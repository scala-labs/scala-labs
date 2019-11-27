package org.scalalabs.basic.lab03

import java.io.{IOException, InputStream}

import scala.io.Source
import scala.util.{Success, Try}
import sys._

object TryExercise01 {

  /**
   * Implement the print method that should output the contents of the input stream to STDOUT. Make sure to close
   * the resource after reading it using the close method provided by the {@code Closeable} interface. Handle
   * potential exceptions using Try and its recover functionality.
   *
   * Hint: You can use {@code Source.fromInputStream} to create a {@Code BufferedSource} and use its
   * convenience methods
   *
   * Expected output in STDOUT:
   *
   * - Happy flow: "Output: <contents of the stream>"
   * - IOException during read: "Output: Couldn't read input stream!"
   * - IOException during close: "Output: <contents of the stream>. Error: Failed to close! "
   */
  def print(inputStream: InputStream): Unit = {
    error("fix me")
  }
}