package org.scalalabs.basic.lab02

import org.specs2.mutable.Specification
import ListManipulationExercise01._

/** Lab 02: List operations
  *
  * Scala basic Lists
  *
  * Your job is to implement the functions in object ListManipulationExercise01
  * and classes in such a way that the tests in this suite all succeed.
  *
  * Hint:
  *   - the methods in ListManipulationExercise01 can all be implemented in
  *     various ways: -- 'built in' functionality in Scala's collection classes
  *     -- pattern matching -- 'functional' style, using recursion, and/or folds
  *
  * It's a nice exercise to try out various ways
  */
class ListManipulationExercise01Test extends Specification {

  val listOfStrings: List[String] = List("One", "Two", "Three")
  "A Scala List" should {

    "get first Element in list" in {
      val result: String = firstElementInList(listOfStrings)
      "One" === result
    }

    "calculate sum of list" in {
      15 === sumOfList(List(1, 2, 3, 4, 5))
    }

    "get last element in list" in {
      "Three" === lastElementInList(listOfStrings)
      9 === lastElementInList(List(1, 6, 10, 33, 54, 9))
    }

    "get nth element in list" in {
      "One" === nthElementInList(0, listOfStrings)
      "Two" === nthElementInList(1, listOfStrings)
      "Three" === nthElementInList(2, listOfStrings)
    }

    "concat two lists" in {
      List("One", "Two", "Three", "Four", "Five") === concatLists(
        listOfStrings,
        List("Four", "Five")
      )
    }

    "contains One Two And Three" in {
      elementExists(listOfStrings, "One")
      elementExists(listOfStrings, "Two")
      elementExists(listOfStrings, "Three")
    }

    "sort list of strings" in {
      val l = List("Sjors", "Arjan", "Age", "Lieke", "J-Fall", "ScalaLabs")
      List(
        "Age",
        "Arjan",
        "J-Fall",
        "Lieke",
        "ScalaLabs",
        "Sjors"
      ) === sortList(l)
    }

    "get list contains two odd elements" in {
      List(1, 3, 5) === oddElements(List(1, 2, 3, 4, 5))
    }

    "get tails of list" in {
      List(
        List(1, 2, 3, 4, 5),
        List(2, 3, 4, 5),
        List(3, 4, 5),
        List(4, 5),
        List(5),
        List()
      ) === tails(List(1, 2, 3, 4, 5))
    }
  }

}
