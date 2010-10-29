package org.scalalabs.basic.lab03

/**
 * This excersice introduces you to the powerful pattern matching features of Scala.
 *
 * Pattern matching can in its essence be compared to Java's 'switch' statement, even though it provides
 * many more possibilites. Whereas the Java switch statmenet lets you 'match' primitive types up to int's,
 * Scala's pattern matching goes much further. Practically everything from all types of objects and Collections
 * can be matched, not forgetting xml and a special type of class called case classes.
 *
 * Pattern matching is also often used in combination with recursive algorithms.
 *
 * For this exercise exclusively use pattern matching constructs in order to make the corresponding unittest work.
 *
 * Reference material to solve these exercises can be found here:
 * Pattern matching in general: http://programming-scala.labs.oreilly.com/ch03.html#PatternMatching
 * Pattern matching in combination with partial functions: http://programming-scala.labs.oreilly.com/ch08.html#PartialFunctions
 */

object Exercise07 {

  /*************************************************************************
   * Basic pattern matching exercises
   * For expected solution see unittest @Exercise07Test
   *************************************************************************/

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
    case o: Option[_] => "A Scala Option subtype"
    case a: AnyRef => "Some Scala class"
    case _ => "A null value"
  }


  def older(p: Person): Option[String] = p match {
    case Person(name, age) if age > 30 => Some(name)
    case _ => None
  }


  /*************************************************************************
   * Pattern matching with partial functions
   * For expected solution see @Exercise07Test
   *************************************************************************/
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


  /*************************************************************************
   * Recursive algorithms with pattern matching
   * For expected solution see unittest @Exercise07Test
   *************************************************************************/
  def compress[T](in: List[T]): List[T] = {
    //built in:
    // in.distinct
    in match {
      case Nil => Nil
      case a :: b :: rest if a == b => compress(a :: rest)
      case a :: rest => a :: compress(rest)
    }
  }

  def groupConsecutive[T](in: List[T]): List[List[T]] = {
    in match {
      case Nil => Nil
      case (head :: _) => val (same, rest) = in.span(_ == head)
      same :: groupConsecutive(rest)
    }
  }

  def groupEquals[T](in: List[T]): List[List[T]] = {
    in match {
      case Nil => Nil
      case (head :: _) => val (same, rest) = in.partition(_ == head)
      same :: groupEquals(rest)
    }
  }

  def amountEqualMembers[T](in: List[T]): List[(Int, T)] = {
    groupEquals(in).map((l: List[T]) => (l.size, l.head))
  }

  def zipMultiple(in: List[List[_]]): List[List[_]] = {

    def flipAll(as: List[List[_]]): List[List[_]] = {
      as match {
        case Nil :: _ => Nil
        case xs => mergeFirstElement(xs) :: flipAll(removeFirstElement(xs))
      }
    }

    def mergeFirstElement(as: List[List[_]]): List[_] = {
      as match {
        case Nil => Nil
        case xs :: rest => xs.head :: mergeFirstElement(rest)
      }
    }

    def removeFirstElement(as: List[List[_]]): List[List[_]] = {
      as match {
        case Nil => Nil
        case xs :: rest => xs.tail :: removeFirstElement(rest)
      }
    }
    flipAll(in)
  }

  def zipMultipleWithDifferentSize(in: List[List[_]]): List[List[_]] = {
    val minLength = in.sortBy(_.size).head.size
    def dropAllListElementsLongerThan(in: List[List[_]], maxLength: Int) = {
      in.map(_.take(maxLength))
    }
    zipMultiple(dropAllListElementsLongerThan(in, minLength))
  }

}

case class Person(name: String, age: Int)