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


  def separateTheMenFromTheBoys(persons: List[Person]): List[List[String]] = {
    def sortByAgeAndMapToName(persons: List[Person]) = persons.sortBy(_.age).map(_.firstName) 
    val (minors, adults) = persons.partition(_.age < 18)
    List(sortByAgeAndMapToName(minors), sortByAgeAndMapToName(adults))
  }


}