package org.scalalabs.basic.lab03

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

/**
 * @see BasicFunctionsExercise1/2
 */
@RunWith(classOf[JUnitRunner])
class BasicFunctionsExerciseTest extends Specification {

  "BasicFunctionsExercise1" should {
    "measure execution time" in {
      4 ==== BasicFunctionsExercise1.measure{
        Thread.sleep(3)
        4
      }
      BasicFunctionsExercise1.printed startsWith ("The execution took: ")
    }
  }
  "BasicFunctionsExercise2" should {
    "increment value with plusOne method" in {
      3 == BasicFunctionsExercise2.plusOne(2)
      6 == BasicFunctionsExercise2.plusOne(5)
    }

    "control structure that closes closable with using method" in {
      //write a control structure that automatically closes any class that has a close method

      //a more real world example than given here would be a reader, or JDBC connection, or anything else that is closable:
      //val reader = new BufferedReader(new FileReader("myFile.txt"))
      val closable = new Closable
      val anotherClosable = new AnotherClosable
      closable.closed must beFalse
      anotherClosable.closed must beFalse

      val greeting = BasicFunctionsExercise2.using(closable) {
        c => c sayHello ("John")
      }
      val anotherGreeting = BasicFunctionsExercise2.using(anotherClosable) {
        c => c sayHello ("John")
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