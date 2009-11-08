package org.scalalabs.basic.lab02

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import Exercise05._

/*
 * Lab 02: more Scala collection operations
 * 
 * Scala advanced Lists
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 *
  * One exercise consists of rewriting imperatively written code to a style that is more functional. 
 */


class Exercise05Test extends JUnitSuite {

  @Test
  def maxIntInList() {
    assert(9 === Exercise05.maxElementInList(List(1, 9, 4, 9, 8)))
    assert(25 === Exercise05.maxElementInList(List(1, 7, 5, 17, 25, 24, 22, 19)))
  }

  @Test
  def rewriteImperativeToFunctional() {
     //This unit test succeeds! But, the code that is called is written 'Java style',
    //it contains a lot of boilerplate code. Your job is to rewrite the code, get rid of the
    //loops and variabels, and use only functions.
    val anton1 = Person(15, "Anton1", "Jansen")
    val anton2 = Person(17, "Anton2", "Janssen")
    val anton3 = Person(18, "Anton3", "Jansssen")
    val peter1 = Person(17, "Peter1", "Peterson")
    val peter2 = Person(19, "Peter2", "Petersson")
    val jason = Person(21, "Jason", "Jasonsson")
    
    val result = Exercise05.separateTheMenFromTheBoys(List(jason, anton1, anton2, anton3, peter1, peter2))
    assert(List(List("Anton1", "Anton2", "Peter1"), List("Anton3", "Peter2", "Jason")) === result)

  }
}