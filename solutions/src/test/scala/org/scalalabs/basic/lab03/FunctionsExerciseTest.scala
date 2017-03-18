package org.scalalabs.basic.lab03

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

/**
 * @see FunctionsExercise1/2
 */
@RunWith(classOf[JUnitRunner])
class FunctionsExerciseTest extends Specification {

  "FunctionsExercise01" should {
    "measure execution time" in {
      def block = {
        Thread.sleep(100)
        4
      }
      4 ==== FunctionsExercise01.measure(block)
      FunctionsExercise01.printed must beMatching("""The execution took: ([1-9][0-9][0-9]) ms""")
    }
  }
  "FunctionsExercise2" should {
    "increment value with plusOne method" in {
      3 == FunctionsExercise02.plusOne(2)
      6 == FunctionsExercise02.plusOne(5)
    }

    "control structure that closes closable with using method" in {
      //write a control structure that automatically closes any class that has a close method

      //a more real world example than given here would be a reader, or JDBC connection, or anything else that is closable:
      //val reader = new BufferedReader(new FileReader("myFile.txt"))
      val closable = new Closable
      val anotherClosable = new AnotherClosable
      closable.closed must beFalse
      anotherClosable.closed must beFalse

      val greeting = FunctionsExercise02.using(closable) {
        c ⇒ c sayHello ("John")
      }
      val anotherGreeting = FunctionsExercise02.using(anotherClosable) {
        c ⇒ c sayHello ("John")
      }

      closable.closed must beTrue
      anotherClosable.closed must beTrue
      greeting === "Hello, John"
      anotherGreeting === "Hello again, John"
    }
  }

}

class Closable {
  var closed = false;

  def close(): Unit = {
    closed = true
  }

  def sayHello(toWho: String): String = {
    "Hello, " + toWho
  }
}

class AnotherClosable {
  var closed = false;

  def close(): Unit = {
    closed = true
  }

  def sayHello(toWho: String): String = {
    "Hello again, " + toWho
  }
}