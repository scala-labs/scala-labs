package org.scalalabs.basic.lab03

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._
import Exercise06._
import java.io.{FileReader, BufferedReader}

/**
 * Fix the tests by implementing some functions given in Exercise06
 */

class Exercise06Test extends JUnitSuite {

  @Test
  def increment() {
    assert(3 == Exercise06.plusOne(2))
    assert(6 == Exercise06.plusOne(5))
  }

  @Test
  def controlStructureShouldCloseClass {
    //write a control structure that automatically closes anything that has a close method

    //a more real world example than given here would be a reader, or JDBC connection, or anything else that is closable:
    //val reader = new BufferedReader(new FileReader("myFile.txt"))
    val closable = new Closable()
    assertFalse(closable closed)

    val greeting = Exercise06.using(closable) {
       c => c.sayHello("John")
    }
    assert(greeting === "Hello, John")
    assertTrue(closable closed)

    val anotherClosable = new AnotherClosable()
    assertFalse(anotherClosable closed)

    val anotherGreeting = Exercise06.using(anotherClosable) {
       c => c.sayHello("John")
    }
    assert(anotherGreeting === "Hello again, John")
    assertTrue(anotherClosable closed)
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