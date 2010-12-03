package org.scalalabs.basic.lab03

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._


/**
 * @see BasicFunctionsExercise
 */

class BasicFunctionsExerciseTest extends JUnitSuite {

  @Test
  def increment() {
    assert(3 == BasicFunctionsExercise.plusOne(2))
    assert(6 == BasicFunctionsExercise.plusOne(5))
  }

  @Test
  def controlStructureShouldCloseClass {
    //write a control structure that automatically closes any class that has a close method

    //a more real world example than given here would be a reader, or JDBC connection, or anything else that is closable:
    //val reader = new BufferedReader(new FileReader("myFile.txt"))
    val closable = new Closable
    val anotherClosable = new AnotherClosable
    assertFalse(closable closed)
    assertFalse(anotherClosable closed)

    val greeting = BasicFunctionsExercise.using(closable) {
      c => c sayHello("John")
    }
    val anotherGreeting = BasicFunctionsExercise.using(anotherClosable) {
       c => c sayHello("John")
    }

    assertTrue(closable closed)
    assertTrue(anotherClosable closed)
    assert(greeting === "Hello, John")
    assert(anotherGreeting === "Hello again, John")
  }
}


class Closable {
  var closed = false;
  
  def close() : Unit = {
    closed = true
  }

  def sayHello(toWho:String):String = {
    "Hello, " + toWho
  }
}

class AnotherClosable {
  var closed = false;

  def close() : Unit = {
    closed = true
  }

  def sayHello(toWho:String):String = {
    "Hello again, " + toWho
  }
}