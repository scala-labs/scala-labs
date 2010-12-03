package org.scalalabs.basic.lab02

import org.scalatest.junit.JUnitSuite
import org.junit.Test

/*
 * Lab 02: List operations
 * 
 * Scala basic Lists
 *
 * Your job is to implement the functions in object BasicListManipulationExercise01 and classes in
 * such a way that the tests in this suite all succeed.
 * 
 * Hint: 
 * - the methods in BasicListManipulationExercise01 can all be implemented in various ways:
 *   -- 'build in' functionality in Scala's collection classes
 *   -- pattern matching
 *   -- 'functional' style, using recursion, and/or folds
 * It's a nice exercise to try out various ways
 */

class BasicListManipulationExercise01Test extends JUnitSuite {
  val listOfStrings: List[String] = List("One", "Two", "Three")

  @Test
  def firstElementInList() {
    val result: String = BasicListManipulationExercise01.firstElementInList(listOfStrings)
    assert("One" === result)
  }

  @Test
  def sumOfList() {
    assert(15 === BasicListManipulationExercise01.sumOfList(List(1, 2, 3, 4, 5)))
  }


  @Test
  def lastElementInList() {
    assert("Three" === BasicListManipulationExercise01.lastElementInList(listOfStrings))
    assert(9 === BasicListManipulationExercise01.lastElementInList(List(1, 6, 10, 33, 54, 9)))
  }

  @Test
  def nthElementInList() {
    assert("One" === BasicListManipulationExercise01.nthElementInList(0, listOfStrings))
    assert("Two" === BasicListManipulationExercise01.nthElementInList(1, listOfStrings))
    assert("Three" === BasicListManipulationExercise01.nthElementInList(2, listOfStrings))
  }

  @Test
  def concatTwoLists() {
    assert(List("One", "Two", "Three", "Four", "Five") === BasicListManipulationExercise01.concatLists(listOfStrings, List("Four", "Five")))
  }

  @Test
  def listContainsOneTwoAndThree() {
    assert(BasicListManipulationExercise01.elementExists(listOfStrings, "One"))
    assert(BasicListManipulationExercise01.elementExists(listOfStrings, "Two"))
    assert(BasicListManipulationExercise01.elementExists(listOfStrings, "Three"))
  }

  @Test
  def sortListOfStrings() {
    val l = List("Sjors", "Arjan", "Age", "Lieke", "J-Fall", "ScalaLabs")
    assert(List("Age", "Arjan", "J-Fall", "Lieke", "ScalaLabs", "Sjors") === BasicListManipulationExercise01.sortList(l))
  }

  @Test
  def listContainsTwoOddElements() {
    assert(List(1, 3, 5) === BasicListManipulationExercise01.oddElements(List(1, 2, 3, 4, 5)))
  }

  @Test
  def tailsOfList() {
    assert(List(List(1, 2, 3, 4, 5), List(2, 3, 4, 5), List(3, 4, 5), List(4, 5), List(5), List()) === BasicListManipulationExercise01.tails(List(1, 2, 3, 4, 5)))
  }

}
