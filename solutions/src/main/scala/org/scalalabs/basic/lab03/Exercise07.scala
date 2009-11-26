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
    s match {
      case "Clojure" | "Haskell" | "Erlang" => "Functional"
      case "Scala" => "Hybrid"
      case "Java" | "Smalltalk" => "OOP"
      case "C" => "Procedural"
      case _ => "Unknown"
    }
  }

  def matchOnInputType(in: Any) = in match {
    case s: String => "A string with length " + s.length
    case i: Int if i > 0 => "A positive integer"
    case i: Int if i < 0 => "A negative integer"
    case o: Option[Any] => "A Scala Option subtype"
    case a: AnyRef => "Some Scala class"
    case _ => "A null value"
  }


  val pf1: PartialFunction[String, String] = {
    case "scala-labs" => "Got scala-labs"
    case "stuff" => "Got stuff"
  }

  val pf2: PartialFunction[String, String] = {
    case "other stuff" => "Got stuff"
  }

  val pf3 = {
    pf1 orElse pf2
  }


  def older(p: Person): Option[String] = p match {
    case Person(name, age) if age > 30 => Some(name)
    case _ => None
  }

  def compress[T](in: List[T]): List[T] = in match {
    case Nil => Nil
    case a :: b :: rest if a == b => compress(a :: rest)
    case a :: rest => a :: compress(rest)
  }

}

case class Person(name: String, age: Int)