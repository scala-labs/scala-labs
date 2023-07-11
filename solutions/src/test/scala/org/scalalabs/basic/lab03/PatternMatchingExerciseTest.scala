package org.scalalabs.basic.lab03

import org.scalalabs.basic.lab03.PatternMatchingExercise01._
import org.scalalabs.basic.lab03.PatternMatchingExercise02._
import org.scalalabs.basic.lab03.PatternMatchingExerciseOther._
import org.specs2.mutable.Specification

/** @see
  *   PatternMatchingExercise
  */
class PatternMatchingExerciseTest extends Specification {

  "PatternMatchingExercise01" should {

    "match on input type" in {
      "A string with length 8" === matchOnInputType("A String")
      "A positive integer" === matchOnInputType(10)
      "A person with name: Jack" === matchOnInputType(Person("Jack", 39))
      "Seq with more than 10 elements" === matchOnInputType(1 to 11 toSeq)
      "first: first, second: second, rest: List(third, fourth)" === matchOnInputType(
        Seq("first", "second", "third", "fourth")
      )
      "A Scala Option subtype" === matchOnInputType(Some(1))
      "A Scala Option subtype" === matchOnInputType(None)
      "Some Scala class" === matchOnInputType(10L)
      "A null value" === matchOnInputType(null)
    }
  }
  "PatternMatchingExercise02" should {

    "transform messages matching the partial function and keep count of transformations" in {
      val transformer = new MessageTransformer({
        case x: Int    => x.toString
        case x: String => x.length
      })
      transformer.process("Say") ==== 3
      transformer.process("Hi") ==== 2
      transformer.process(5) ==== "5"
      transformer.process('a') ==== 'a'
      transformer.transformationCountBy(classOf[String]) ==== 2
      transformer.transformationCountBy(classOf[Integer]) ==== 1
      transformer.transformationCountBy(classOf[Symbol]) ==== 0
    }

  }
  "PatternMatchingExerciseOther" should {
    "match language on strings" in {
      "OOP" === describeLanguage("Java")
      "OOP" === describeLanguage("Smalltalk")
      "Functional" === describeLanguage("Clojure")
      "Functional" === describeLanguage("Haskell")
      "Hybrid" === describeLanguage("Scala")
      "Procedural" === describeLanguage("C")
      "Unknown" === describeLanguage("Oz")
    }
    "check age" in {
      Some("Jack") === older(new Person("Jack", 31))
      None === older(new Person("Jack", 30))
    }
    "match partial functions" in {
      // pf1 and pf2 are both partial functions.
      // These inherit from Scala's Function class, with an extra method: isDefinedAt
      //  pf3 should be defined in terms of pf1 and pf2

      pf1.isDefinedAt("scala-labs") must beTrue
      pf1.isDefinedAt("stuff") must beTrue
      pf1.isDefinedAt("other stuff") must beFalse
      pf2.isDefinedAt("other stuff") must beTrue

      pf3.isDefinedAt("scala-labs") must beTrue
      pf3.isDefinedAt("other stuff") must beTrue
    }
  }
}
