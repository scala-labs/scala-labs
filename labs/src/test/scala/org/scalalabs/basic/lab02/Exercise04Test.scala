package org.scalalabs.basic.lab02

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.junit.Test

/*
 * Lab 02: List operations
 * 
 * Scala basic Lists
 *
 * Your job is to implement the functions in object Exercise04 and classes in
 * such a way that the tests in this suite all succeed.
 * 
 * Hint: 
 * - the methods in Exercise04 can all be implemented in various ways:
 *   -- 'built in' functionality in Scala's collection classes
 *   -- pattern matching
 *   -- 'functional' style, using recursion, and/or folds
 * 
 * It's a nice exercise to try out various ways
 */

class Exercise04Test extends JUnitSuite {
  val listOfStrings: List[String] = List("One", "Two", "Three")

  @Test
  def firstElementInList() {
    val result: String = Exercise04.firstElementInList(listOfStrings)
    assert("One" === result)
  }

  @Test
  def sumOfList() {
    assert(15 === Exercise04.sumOfList(List(1, 2, 3, 4, 5)))
  }


  @Test
  def lastElementInList() {
    assert("Three" === Exercise04.lastElementInList(listOfStrings))
    assert(9 === Exercise04.lastElementInList(List(1, 6, 10, 33, 54, 9)))
  }

  @Test
  def nthElementInList() {
    assert("One" === Exercise04.nthElementInList(0, listOfStrings))
    assert("Two" === Exercise04.nthElementInList(1, listOfStrings))
    assert("Three" === Exercise04.nthElementInList(2, listOfStrings))
  }

  @Test
  def concatTwoLists() {
    assert(List("One", "Two", "Three", "Four", "Five") === Exercise04.concatLists(listOfStrings, List("Four", "Five")))
  }

  @Test
  def listContainsOneTwoAndThree() {
    assert(Exercise04.elementExists(listOfStrings, "One"))
    assert(Exercise04.elementExists(listOfStrings, "Two"))
    assert(Exercise04.elementExists(listOfStrings, "Three"))
  }

  @Test
  def sortListOfStrings() {
    val l = List("Sjors", "Arjan", "Age", "Lieke", "J-Fall", "ScalaLabs")
    assert(List("Age", "Arjan", "J-Fall", "Lieke", "ScalaLabs", "Sjors") === Exercise04.sortList(l))
  }

  @Test
  def listContainsTwoOddElements() {
    assert(List(1, 3, 5) === Exercise04.oddElements(List(1, 2, 3, 4, 5)))
  }

  @Test
  def tailsOfList() {
    assert(List(List(1, 2, 3, 4, 5), List(2, 3, 4, 5), List(3, 4, 5), List(4, 5), List(5), List()) === Exercise04.tails(List(1, 2, 3, 4, 5)))
  }

}
