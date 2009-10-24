package org.scalalabs.basic.lab02

import _root_.scala.collection.mutable.ListBuffer


case class Person(age: Int, firstName: String, lastName: String)

object Exercise05 {
  def maxElementInList(l: List[Int]): Int = {
    error("fix me")
  }

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
    //TODO can this be made even more imperative?
    var sortedBoys = boys.toList.sort(sortPerson _)
    var sortedMen = men.toList.sort(sortPerson _)
    
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