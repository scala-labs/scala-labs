package org.scalalabs.basic.lab02

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import java.lang.{ IllegalArgumentException => IAE }
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

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
 *   -- 'built in' functionality in Scala's collection classes
 *   -- pattern matching
 *   -- 'functional' style, using recursion, and/or folds
 * 
 * It's a nice exercise to try out various ways
 */
@RunWith(classOf[JUnitRunner])
class BasicListManipulationExercise01Test extends Specification {

  val listOfStrings: List[String] = List("One", "Two", "Three")
  "A Scala List" should {
    
    "get first Element in list" in {
      val result: String = BasicListManipulationExercise01.firstElementInList(listOfStrings)
      "One" === result
    }

    "calculate sum of list" in {
      15 === BasicListManipulationExercise01.sumOfList(List(1, 2, 3, 4, 5))
    }

    "get last element in list" in {
      "Three" === BasicListManipulationExercise01.lastElementInList(listOfStrings)
      9 === BasicListManipulationExercise01.lastElementInList(List(1, 6, 10, 33, 54, 9))
    }

    "get nth element in list" in {
      "One" === BasicListManipulationExercise01.nthElementInList(0, listOfStrings)
      "Two" === BasicListManipulationExercise01.nthElementInList(1, listOfStrings)
      "Three" === BasicListManipulationExercise01.nthElementInList(2, listOfStrings)
    }

    "concat two lists" in {
      List("One", "Two", "Three", "Four", "Five") === BasicListManipulationExercise01.concatLists(listOfStrings, List("Four", "Five"))
    }

    "contains One Two And Three" in {
      BasicListManipulationExercise01.elementExists(listOfStrings, "One")
      BasicListManipulationExercise01.elementExists(listOfStrings, "Two")
      BasicListManipulationExercise01.elementExists(listOfStrings, "Three")
    }

    "sort list of strings" in {
      val l = List("Sjors", "Arjan", "Age", "Lieke", "J-Fall", "ScalaLabs")
      List("Age", "Arjan", "J-Fall", "Lieke", "ScalaLabs", "Sjors") === BasicListManipulationExercise01.sortList(l)
    }

    "get list contains two odd elements" in {
      List(1, 3, 5) === BasicListManipulationExercise01.oddElements(List(1, 2, 3, 4, 5))
    }

    "get tails of list" in {
      List(List(1, 2, 3, 4, 5), List(2, 3, 4, 5), List(3, 4, 5), List(4, 5), List(5), List()) === BasicListManipulationExercise01.tails(List(1, 2, 3, 4, 5))
    }
  }

}
