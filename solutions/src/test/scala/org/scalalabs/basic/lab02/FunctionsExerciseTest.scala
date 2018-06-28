package org.scalalabs.basic.lab02

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

/**
 * @see FunctionsExercise1/2/3
 */
@RunWith(classOf[JUnitRunner])
class FunctionsExerciseTest extends Specification {

  "FunctionsExercise01" should {
    "higher order function that does file resource handling while offering the content of the file as String" in {
      FunctionsExercise01.doWithText(content => content.reverse) ==== FunctionsExercise01.reverseText()
      FunctionsExercise01.doWithText(content => content.toUpperCase) ==== FunctionsExercise01.upperCaseText()
    }
  }
  "FunctionsExercise02" should {
    "measure execution time" in {
      def block = {
        Thread.sleep(100)
        4
      }
      4 ==== FunctionsExercise02.measure(block)
      FunctionsExercise02.printed must beMatching("""The execution took: ([1-9][0-9][0-9]) ms""")
    }
  }
  "FunctionsExercise03" should {
    "increment value with plusOne method" in {
      3 == FunctionsExercise03.plusOne(2)
      6 == FunctionsExercise03.plusOne(5)
    }

    "control structure that closes closable with using method" in {
      //write a control structure that automatically closes any class that has a close method

      //a more real world example than given here would be a reader, or JDBC connection, or anything else that is closable:
      //val reader = new BufferedReader(new FileReader("myFile.txt"))
      val closable = new Closable
      val anotherClosable = new AnotherClosable
      closable.closed must beFalse
      anotherClosable.closed must beFalse

      val greeting = FunctionsExercise03.using(closable) {
        c ⇒ c sayHello ("John")
      }
      val anotherGreeting = FunctionsExercise03.using(anotherClosable) {
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