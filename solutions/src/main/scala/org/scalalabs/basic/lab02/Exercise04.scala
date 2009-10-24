package org.scalalabs.basic.lab02

object Exercise04 {
  def firstElementInList[T](l: List[T]): T = {
    //buildin
      l.first
  }

  def lastElementInList[T](l: List[T]): T = {
    //buildin: l.last
     //almost buildin: l.reverse.head

    //own version: pattern match
     def pLast[T](l: List[T]) : T = {
       l match {
          case head :: Nil => head
          case _ :: tail => pLast(tail)
          case _ => error("last on empty list")
       }
     }

    //own version2: using fold
    def fLast[T](l:List[T]): T = {
      l.foldLeft(l.headOption){(a,b) => Some(b)}.getOrElse(error("last on empty list"))
    }
     fLast(l)
  }

  def nthElementInList[T](n: Int, l: List[T]): T = {
    error("fix me")
  }

  def concatLists(list1: List[Any], list2: List[String]): List[String] = {
    error("fix me")
  }

  def sortList(list: List[String]):List[String] = {
    error("fix me")
  }
  
  def elementExists(theList: List[String], elementToSearchFor: String): Boolean = {
    error("fix me")
  }

  def oddElements(listOfInts: List[Int]): List[Int] = {
    error("fix me")
  }
}


