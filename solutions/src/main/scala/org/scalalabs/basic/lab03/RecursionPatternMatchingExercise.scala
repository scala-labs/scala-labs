package org.scalalabs.basic.lab03
/**
 * This exercise introduces you to pattern matching in combination with recursion.
 *
 * Recursion is a key concept for the functional style programming.
 * In the exercises below you learn how to apply recursion in combination with Scala's pattern matching facilities.
 *
 * For this exercise exclusively use pattern matching constructs in order to make the corresponding unittest work.
 *
 * Reference material to solve these exercises can be found here:
 * Pattern matching in general: http://programming-scala.labs.oreilly.com/ch03.html#PatternMatching
 * Pattern matching and recursion: http://programming-scala.labs.oreilly.com/ch08.html#Recursion
 */

object RecursionPatternMatchingExercise {

  /**
   * ***********************************************************************
   * Recursive algorithms with pattern matching
   * For expected solution see unittest @RecursionPatternMatchingExerciseTest
   * ***********************************************************************
   */
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
      case (head :: _) =>
        val (same, rest) = in.span(_ == head)
        same :: groupConsecutive(rest)
    }
  }

  def groupEquals[T](in: List[T]): List[List[T]] = {
    in match {
      case Nil => Nil
      case (head :: _) =>
        val (same, rest) = in.partition(_ == head)
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



