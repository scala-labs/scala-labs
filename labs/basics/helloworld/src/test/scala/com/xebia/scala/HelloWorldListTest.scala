package com.xebia.scala

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

/*
 * Beginner Hello World:
 * 
 * Scala Lists and basic closures
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 * 
 * Hint: 
 * - the methods in HelloWorldList can all be implemented as one liners
 * - note how a List can be constructed
 */

class HelloWorldListTest extends JUnit3Suite {
  
	def testFirstElementIsOne() {
		assert("One" === HelloWorldList.firstElementInListOfStrings(List("One", "Two", "Three")))
		assert("Two" === HelloWorldList.firstElementInListOfStrings(List("Two", "Three")))
	}
 
	def testConcatOneAndTwoYieldsListOfOneAndTwo() {
		assert(List("One", "Two") === HelloWorldList.concatLists(List("One"), List("Two")))
		assert(List("Two", "Three") === HelloWorldList.concatLists(List("Two"), List("Three")))
	}
 
    def testListContainsOneTwoAndThree() {
	    val helloWorldList:List[String] = List("One", "Two", "Three")
     
        assert(HelloWorldList.elementExists(helloWorldList, "One"))
        assert(HelloWorldList.elementExists(helloWorldList, "Two")) 
        assert(HelloWorldList.elementExists(helloWorldList, "Three"))
    }
    
    def testListContainsTwoOddElements() {
    	assert(2 === HelloWorldList.oddElements(List(1, 2, 3)).size)
    	assert(List(1,3) === HelloWorldList.oddElements(List(1, 2, 3)))
    }

}
