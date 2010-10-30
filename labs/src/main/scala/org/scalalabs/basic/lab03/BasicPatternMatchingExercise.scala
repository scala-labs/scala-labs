package org.scalalabs.basic.lab03

/**
 * This excersice introduces you to the powerful pattern matching features of Scala.
 *
 * Pattern matching can in its essence be compared to Java's 'switch' statement,
 * even though it provides many more possibilites. Whereas the Java switch statmenet
 * lets you 'match' primitive types up to int's, Scala's pattern matching goes much
 * further. Practically everything from all types of objects and Collections
 * can be matched, not forgetting xml and a special type of class called case classes.
 *
 * Pattern matching is also often used in combination with recursive algorithms.
 *
 * For this exercise exclusively use pattern matching constructs in order to make the
 * corresponding unit test work.
 *
 * Reference material to solve these exercises can be found here:
 * Pattern matching in general: http://programming-scala.labs.oreilly.com/ch03.html#PatternMatching
 * Pattern matching in combination with partial functions: http://programming-scala.labs.oreilly.com/ch08.html#PartialFunctions
 */

object BasicPatternMatchingExercise {

  /*************************************************************************
   * Basic pattern matching exercises
   * For expected solution see unittest @BasicPatternMatchingExerciseTest
   *************************************************************************/

  def describeLanguage(s: String) = {
    error("fix me")
  }

  def matchOnInputType(in: Any) = {
    error("fix me")
  }

  def older(p: Person): Option[String] = {
    error("fix me")
  }

  /*************************************************************************
   * Pattern matching with partial functions
   * For expected solution see @BasicPatternMatchingExerciseTest
   *************************************************************************/

  val pf1: PartialFunction[String, String] = {
    error("fix me")
  }

  val pf2: PartialFunction[String, String] = {
    error("fix me")
  }

  val pf3:PartialFunction[String, String] = {
    error("fix me")
  }

                          
  /*************************************************************************
   * Recursive algorithms with pattern matching
   * For expected solution see unittest @BasicPatternMatchingExerciseTest
   *************************************************************************/

  def compress[T](in: List[T]): List[T] = {
    error("fix me")
  }

  def groupConsecutive[T](in: List[T]): List[List[T]] = {
    error("fix me")
  }

  def groupEquals[T](in: List[T]): List[List[T]] = {
    error("fix me")
  }

  def amountEqualMembers[T](in: List[T]): List[(Int, T)] = {
    error("fix me")
  }

  def zipMultiple(in: List[List[_]]): List[List[_]] = {
    error("fix me")
  }

  def zipMultipleWithDifferentSize(in: List[List[_]]): List[List[_]] = {
    error("fix me")
  }

}

case class Person(name: String, age: Int)