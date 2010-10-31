package org.scalalabs.basic.lab02

import _root_.scala.collection.mutable.ListBuffer


case class Person(age: Int, firstName: String, lastName: String)

object BasicListManipulationExercise02 {
  def sumOfElementsInList(l: List[Int]): Int = {
    //different solutions:
    //** List API **
    //l.sum

    //** recursive with match**
    def sum(l: List[Int]): Int = {
      l match {
        case Nil => 0
        case first :: tail => first + sum(tail)
      }
    }
    sum(l)

    //** recursive with if else**
    //def sum2(l:List[Int]):Int = if(l.isEmpty) 0 else l.head + sum2(l.tail)

    //** with fold**    
    //    l.foldRight(0)((a, b) => a + b)
    //    l.foldRight(0)(_ + _)
  }


  def maxElementInList(l: List[Int]): Int = {
    l.foldLeft(0) {(a, b) => if (a < b) b else a}
  }


  def sumOfTwo(l1: List[Int], l2: List[Int]): List[Int] = {
    //use a touple to see wheater one of the element is Nil
    (l1, l2) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      //another way to express the addition of the elements could be
      //with an anonymous function instead of a partial function expressed with case(a, b) etc.
      //case (xs, ys) => xs zip ys map((t:(Int, Int)) => t._1 + t._2)
      case (xs, ys) => xs zip ys map {case (a, b) => a + b}
    }
  }

  /**
   * For this exercise preferably make use of the sumOfTwo
   * method above
   */
  def sumOfMany(l: List[Int]*): List[Int] = {
    //in order to be able to use pattern matching the variable argument List parameter
    //(which is an Array of Lists) needs to be converted to a List of Lists
    //therefore the method beneath needs to be defined
    def sumOfManyNestedLists(l: List[List[Int]]): List[Int] = {
      println(l)
      l match {
        case head :: tail => sumOfTwo(head, sumOfManyNestedLists(tail))
        case Nil => Nil
      }
    }
    sumOfManyNestedLists(l.toList)
  }


  def separateTheMenFromTheBoys(persons: List[Person]): List[List[String]] = {
    def sortByAgeAndMapToName(persons: List[Person]) = persons.sortBy(_.age).map(_.firstName)
    val (minors, adults) = persons.partition(_.age < 18)
    List(sortByAgeAndMapToName(minors), sortByAgeAndMapToName(adults))
  }


}