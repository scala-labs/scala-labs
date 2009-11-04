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

}