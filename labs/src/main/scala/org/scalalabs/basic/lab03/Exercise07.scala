package org.scalalabs.basic.lab03

/**
 * This excersice introduces you to the powerful pattern matching features of Scala.
 *
 * Pattern matching can in its essence be compared to Java's 'switch' statement, even though it provides
 * many more possibilites. Whereas the Java switch statmenet lets you 'match' primitive types up to int's,
 * Scala's pattern matching goes much further. Practically everything from all types of objects and Collections
 * can be matched, not forgetting xml and a special type of class called case classes.
 *
 * For this exercise exclusively use pattern matching constructs in order to make the corresponding unittest work.
 *
 * Reference material to solve these exercises can be found here:
 * Pattern matching in general: http://programming-scala.labs.oreilly.com/ch03.html#PatternMatching
 * Pattern matching in combination with partial functions: http://programming-scala.labs.oreilly.com/ch08.html#PartialFunctions
 */

object Exercise07 {
  def describeLanguage(s: String) = {
    error("fix me")
  }

  def matchOnInputType(in: Any) = {
    error("fix me")
  }


  val pf1: PartialFunction[String, String] = {
    error("fix me")
  }

  val pf2: PartialFunction[String, String] = {
    error("fix me")
  }

  val pf3:PartialFunction[String, String] = {
    error("fix me")
  }


  def older(p: Person): Option[String] = {
    error("fix me")
  }

  def compress[T](in: List[T]): List[T] = {
    error("fix me")
  }

}

case class Person(name: String, age: Int)