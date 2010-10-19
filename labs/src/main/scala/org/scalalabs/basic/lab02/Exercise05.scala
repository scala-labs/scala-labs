package org.scalalabs.basic.lab02

import _root_.scala.collection.mutable.ListBuffer


case class Person(age: Int, firstName: String, lastName: String)

object Exercise05 {

  /**
   * Find the maximum element in a list, e.g. maxElementInList(List(1,9,3,5)) == 9
   * As usual, various ways exist: pattern matching, folding, ...
   */
  def maxElementInList(l: List[Int]): Int = {
    error("fix me")
  }

  /**
   * The following method is implemented in the most in-elegant way we could think of.
   * The idea is to re-write the method into more functional style. In the end, you
   * may be able to achieve the same functionality as implemented below
   * in a one-liner.
   */
  def separateTheMenFromTheBoys(persons: List[Person]): List[List[String]] = {
    var boys: ListBuffer[Person] = new ListBuffer[Person]()
    var men: ListBuffer[Person] = new ListBuffer[Person]()
    var validBoyNames: ListBuffer[String] = new ListBuffer[String]()
    var validMenNames: ListBuffer[String] = new ListBuffer[String]()

    for (person <- persons) {
        if (person.age < 18) {
          boys += person
        } else {
          men += person
        }
    }

    var sortedBoys = boys.toList.sortWith(sortPerson _)
    var sortedMen = men.toList.sortWith(sortPerson _)

    for (boy <- sortedBoys) {
      validBoyNames += boy.firstName
    }
    for (man <- sortedMen) {
      validMenNames += man.firstName
    }
    List(validBoyNames.toList, validMenNames.toList)
  }

  def sortPerson(a: Person, b: Person) = a.age < b.age

}