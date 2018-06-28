package org.scalalabs.basic.lab03

import sys._

/**
  * This exercise introduces you to the powerful pattern matching features of Scala.
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

/**
  * ***********************************************************************
  * pattern matching exercises
  * For expected solution see unittest @PatternMatchingExerciseTest
  * ***********************************************************************
  */
object PatternMatchingExercise01 {

  case class Person(name: String, age: Int)


  def matchOnInputType(in: Any) = {
    error("fix me")
  }

}

/**
  * ***********************************************************************
  * Partial functions exercise.
  * The MessageTransformer must use the PartialFunction[Any, Any] called transform to transform messages in its process(msg:Any) method
  * - For every input message that can be transformed update the count using updateCount(...)
  * - Messages that cannot be transformed must be returned as is, no count is updated.
  * Provide an implementation only using PartialFunctions (if statements are not allowed) to make the unittest succeed.
  * For expected behaviour see @PatternMatchingExerciseTest
  * ***********************************************************************
  */
object PatternMatchingExercise02 {


  class MessageTransformer(private val transform: PartialFunction[Any, Any]) {

    private var transformationCount: Map[Class[_], Int] = Map().withDefaultValue(0)

    def process(message: Any): Any = {
      error("fix me")
    }

    private def updateCount(message: Any) = {
      transformationCount = transformationCount + (message.getClass -> (transformationCount(message.getClass) + 1))
    }

    def transformationCountBy(clazz: Class[_]): Int = transformationCount(clazz)


  }

}

