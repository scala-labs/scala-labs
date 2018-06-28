package org.scalalabs.basic.lab03

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import PatternMatchingExercise01._
import PatternMatchingExercise02._

/**
  * @see PatternMatchingExercise
  */
@RunWith(classOf[JUnitRunner])
class PatternMatchingExerciseTest extends Specification {

  "PatternMatchingExercise01" should {

    "match on input type" in {
      "A string with length 8" === matchOnInputType("A String")
      "A positive integer" === matchOnInputType(10)
      "A person with name: Jack" === matchOnInputType(Person("Jack", 39))
      "Seq with more than 10 elements" === matchOnInputType(1 to 11 toSeq)
      "first: first, second: second, rest: List(third, fourth)" === matchOnInputType(Seq("first", "second", "third", "fourth"))
      "A Scala Option subtype" === matchOnInputType(Some(1))
      "A Scala Option subtype" === matchOnInputType(None)
      "Some Scala class" === matchOnInputType(10l)
      "A null value" === matchOnInputType(null)
    }
  }
  "PatternMatchingExercise02" should {

    "transform messages matching the partial function and keep count of transformations" in {
      val transformer = new MessageTransformer({
        case x: Int => x.toString
        case x:String => x.length
      })
      transformer.process("Say") ==== 3
      transformer.process("Hi") ==== 2
      transformer.process(5) ==== "5"
      transformer.process('a) ==== 'a
      transformer.transformationCountBy(classOf[String]) ==== 2
      transformer.transformationCountBy(classOf[Int]) ==== 1
      transformer.transformationCountBy(classOf[Symbol]) ==== 0
    }

  }
}