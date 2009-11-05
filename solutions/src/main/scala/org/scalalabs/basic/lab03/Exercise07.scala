package org.scalalabs.basic.lab03

/**
 * Some pattern matching
 */

object Exercise07 {

    def describeLanguage(s:String) = {
      s match {
        case "Clojure" | "Haskell" | "Erlang" => "Functional"
        case "Scala" => "Hybrid"
        case "Java" | "Smalltalk"=> "OOP"
        case "C" => "Procedural"
        case _ => "Unknown"
      }
    }

  def matchOnInputType(in:Any) = in match {
    case s: String => "A string with length " + s.length
    case i: Int if i > 0 => "A positive integer"
    case i: Int if i < 0 => "A negative integer"
    case o: Option[Any] => "A Scala Option subtype"
    case a: AnyRef => "Some Scala class"
    case _ => "A null value"
  }

}