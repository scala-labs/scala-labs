package org.scalalabs.basic.lab03

/**
 * Some pattern matching
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