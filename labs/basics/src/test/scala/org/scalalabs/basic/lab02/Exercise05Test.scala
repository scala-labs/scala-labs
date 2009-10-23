package org.scalalabs.basic.lab02

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import Exercise05._

/*
 * Lab 02: some more advanced Scala collection operations
 * 
 * Scala advanced Lists
 *
 * Your job is to implement the objects and classes in
 * such a way that the tests in this suite all succeed.
 * 
 * Hint: 
 * - the methods in Exercise05 can all be implemented in various ways:
 *   -- build in List functionality
 *   -- pattern matching
 *   -- 'functional' style, using recursion, and/or folds
 * - note how a List can be constructed
 */


class Exercise05Test extends JUnitSuite {

  @Test
  def maxIntInList() {
    assert(25 === maxElementInList(List(1, 7, 5, 17, 25, 24, 22, 19)))
  }

  @Test
  def 

}