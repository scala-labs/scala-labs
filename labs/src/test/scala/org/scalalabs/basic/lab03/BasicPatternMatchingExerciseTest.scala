package org.scalalabs.basic.lab03

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

/**
 * @see BasicPatternMatchingExercise
 */
@RunWith(classOf[JUnitRunner])
class BasicPatternMatchingExerciseTest extends Specification {

  "BasicPatternMatchingExercise" should {
    "match language on strings" in {
      "OOP" === BasicPatternMatchingExercise.describeLanguage("Java")
      "OOP" === BasicPatternMatchingExercise.describeLanguage("Smalltalk")
      "Functional" === BasicPatternMatchingExercise.describeLanguage("Clojure")
      "Functional" === BasicPatternMatchingExercise.describeLanguage("Haskell")
      "Hybrid" === BasicPatternMatchingExercise.describeLanguage("Scala")
      "Procedural" === BasicPatternMatchingExercise.describeLanguage("C")
      "Unknown" === BasicPatternMatchingExercise.describeLanguage("Oz")
    }
    "match on input type" in {
      "A string with length 8" === BasicPatternMatchingExercise.matchOnInputType("A String")
      "A positive integer" === BasicPatternMatchingExercise.matchOnInputType(10)
      "A person with name: Jack" === BasicPatternMatchingExercise.matchOnInputType(Person("Jack", 39))
      "Seq with more than 10 elements" === BasicPatternMatchingExercise.matchOnInputType(1 to 11 toSeq)
      "first: first, second: second, rest: List(third, fourth)" === BasicPatternMatchingExercise.matchOnInputType(Seq("first", "second", "third", "fourth"))
      "A Scala Option subtype" === BasicPatternMatchingExercise.matchOnInputType(Some(1))
      "A Scala Option subtype" === BasicPatternMatchingExercise.matchOnInputType(None)
      "Some Scala class" === BasicPatternMatchingExercise.matchOnInputType(10l)
      "A null value" === BasicPatternMatchingExercise.matchOnInputType(null)
    }
    "check age" in {
      Some("Jack") === BasicPatternMatchingExercise.older(new Person("Jack", 31))
      None === BasicPatternMatchingExercise.older(new Person("Jack", 30))
    }
    "match partial functions" in {
      //pf1 and pf2 are both partial functions.
      //These inherit from Scala's Function class, with an extra method: isDefinedAt
      //  pf3 should be defined in terms of pf1 and pf2

      BasicPatternMatchingExercise.pf1.isDefinedAt("scala-labs") must beTrue
      BasicPatternMatchingExercise.pf1.isDefinedAt("stuff") must beTrue
      BasicPatternMatchingExercise.pf1.isDefinedAt("other stuff") must beFalse
      BasicPatternMatchingExercise.pf2.isDefinedAt("other stuff") must beTrue

      BasicPatternMatchingExercise.pf3.isDefinedAt("scala-labs") must beTrue
      BasicPatternMatchingExercise.pf3.isDefinedAt("other stuff") must beTrue
    }
  }
}