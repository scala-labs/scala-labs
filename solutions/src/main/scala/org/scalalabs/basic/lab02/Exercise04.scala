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
    def myLast1[T](l: List[T]): T = {
      l match {
        case head :: Nil => head
        case _ :: tail => myLast1(tail)
        case _ => error("last on empty list")
      }
    }

    //custom version2: using fold
    def myLast2[T](l: List[T]): T = {
      l.foldLeft(l.headOption) {(a, b) => Some(b)}.getOrElse(error("last on empty list"))
    }
    myLast1(l)
  }

  def nthElementInList[T](n: Int, l: List[T]): T = {
    //solution using zipWithIndex
    def myNth1(n: Int, l: List[T]): T = {
      l.zipWithIndex.filter(p => p._2 == n).headOption.getOrElse(error("index out of bounds"))._1
    }
    myNth1(n, l)
  }

  def concatLists[T](l1: List[T], l2: List[T]): List[T] = {
    //build in: l1 ::: l2
    def myConcat(l1: List[T], l2: List[T]):List[T] = {
      l1 match {
        case Nil => l2
        case x :: xs => x :: myConcat(xs, l2)
      }
    }
    myConcat(l1, l2)
  }

  def sortList(list: List[String]): List[String] = {
    error("fix me")
  }

  def elementExists(theList: List[String], elementToSearchFor: String): Boolean = {
    error("fix me")
  }

  def oddElements(listOfInts: List[Int]): List[Int] = {
    error("fix me")
  }

  def tails[T](l: List[T]): List[List[T]] = {
    if (l.isEmpty) List(Nil)
    else l :: tails(l.tail)
  }
}


