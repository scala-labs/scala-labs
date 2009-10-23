package org.scalalabs.basic.lab01

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.junit.Test

/*
 * Beginner Hello World:
 * 
 * Scala Lists and basic closures
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 * 
 * Hint: 
 * - the methods in Lab01List can all be implemented as one liners
 * - note how a List can be constructed
 */

class Exercise04 extends JUnitSuite {

  @Test
	def firstElementInList() {
		assert("One" === Lab01List.firstElementInListOfStrings(List("One", "Two", "Three")))
		assert("Two" === Lab01List.firstElementInListOfStrings(List("Two", "Three")))
	}

    @Test
	def concatOneAndTwoYieldsListOfOneAndTwo() {
		assert(List("One", "Two") === Lab01List.concatLists(List("One"), List("Two")))
		assert(List("Two", "Three") === Lab01List.concatLists(List("Two"), List("Three")))
	}


  @Test def listContainsOneTwoAndThree() {
	    val helloWorldList:List[String] = List("One", "Two", "Three")
     
        assert(Lab01List.elementExists(helloWorldList, "One"))
        assert(Lab01List.elementExists(helloWorldList, "Two"))
        assert(Lab01List.elementExists(helloWorldList, "Three"))
    }
    
    @Test def listContainsTwoOddElements() {
    	assert(2 === Lab01List.oddElements(List(1, 2, 3)).size)
    	assert(List(1,3) === Lab01List.oddElements(List(1, 2, 3)))
    }

}
