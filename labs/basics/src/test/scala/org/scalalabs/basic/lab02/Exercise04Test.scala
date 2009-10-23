package org.scalalabs.basic.lab02

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.junit.Test
import Exercise04._

/*
 * Lab 02: List functions
 * 
 * Scala Lists and basic closures
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 * 
 * Hint: 
 * - the methods in Exercise04 can all be implemented in various ways:
 *   -- build in List functionality
 *   -- pattern matching
 *   -- 'functional' style, using recursion, and/or folds
 * - note how a List can be constructed
 */

class Exercise04Test extends JUnitSuite {
  val listOfStrings = List("One", "Two", "Three")

  @Test
	def firstElementInList() {
		assert("One" === firstElementInList(listOfStrings))
	}

  @Test
	def lastElementInList() {
		assert("Three" === lastElementInList(listOfStrings))
		assert("Two" === lastElementInList(List("Two", "Three")))
	}

  @Test
	def nthElementInList() {
		assert("One" === nthElementInList(1, listOfStrings))
		assert("Two" === nthElementInList(2, listOfStrings))
    assert("Three" === nthElementInList(3, listOfStrings))
	}
  
  @Test
	def concatTwoLists() {
		assert(List("One", "Two", "Three", "Four", "Five") === concatLists(listOfStrings, List("Four", "Five")))
	}

  @Test def listContainsOneTwoAndThree() {
	    val helloWorldList:List[String] = List("One", "Two", "Three")
     
        assert(elementExists(helloWorldList, "One"))
        assert(elementExists(helloWorldList, "Two"))
        assert(elementExists(helloWorldList, "Three"))
    }
    
    @Test def listContainsTwoOddElements() {
    	 assert(List(1,3,5) === oddElements(List(1, 2, 3, 4, 5)))
    }

}
