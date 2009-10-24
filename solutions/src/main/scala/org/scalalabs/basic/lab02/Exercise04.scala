package org.scalalabs.basic.lab02

object Exercise04 {
  def firstElementInList[T](l: List[T]): T = {
    //buildin
      l.first
  }

  def lastElementInList[T](l: List[T]): T = {
    //buildin: l.last
     //almost buildin: l.reverse.head

    //custom version: pattern match
     def myLast1[T](l: List[T]) : T = {
       l match {
          case head :: Nil => head
          case _ :: tail => myLast1(tail)
          case _ => error("last on empty list")
       }
     }

    //custom version2: using fold
    def myLast2[T](l:List[T]): T = {
      l.foldLeft(l.headOption){(a,b) => Some(b)}.getOrElse(error("last on empty list"))
    }
     myLast1(l)
  }

  def nthElementInList[T](n: Int, l: List[T]): T = {
    //solution using zipWithIndex
    def myNth1(n:Int, l:List[T]): T = {
       l.zipWithIndex.filter(p => p._2 == n).headOption.getOrElse(error("index out of bounds"))._1
    }
    myNth1(n, l)
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


