package org.scalalabs.basic.lab02
import scala.annotation.tailrec
import sys._
object ListManipulationExercise01 {
  def firstElementInList[T](l: List[T]): T = {
    // built in
    l.head
  }

  def sumOfList(l: List[Int]): Int = {
    // almost built in
    //    l.foldLeft(0)((a,b) => a+b)

    // pattern match solution:
    @tailrec
    def mySum(acc: Int, curList: List[Int]): Int = {
      curList match {
        case Nil     => acc
        case x :: xs => mySum((acc + x), xs)
      }
    }

    mySum(0, l)
  }

  def lastElementInList[T](l: List[T]): T = {
    //    built in:
    l.last
    //    almost built in:
    l.reverse.head

    // custom version: pattern match
    @tailrec
    def myLast1[T](l: List[T]): T = {
      l match {
        case head :: Nil => head
        case _ :: tail   => myLast1(tail)
        case _           => error("last on empty list")
      }
    }

    // custom version2: using fold
    def myLast2[T](l: List[T]): T = {
      l.foldLeft(l.headOption) { (a, b) => Some(b) }
        .getOrElse(error("last on empty list"))
    }
    myLast1(l)
  }

  def nthElementInList[T](n: Int, l: List[T]): T = {
    // solution using zipWithIndex
    def myNth1(n: Int, l: List[T]): T = {
      l.zipWithIndex
        .find(p => p._2 == n)
        .getOrElse(error("index out of bounds"))
        ._1
    }
    myNth1(n, l)
  }

  def concatLists[T](l1: List[T], l2: List[T]): List[T] = {
    // built in: l1 ::: l2
    def myConcat(l1: List[T], l2: List[T]): List[T] = {
      l1 match {
        case Nil     => l2
        case x :: xs => x :: myConcat(xs, l2)
      }
    }
    myConcat(l1, l2)
  }

  def sortList[T](list: List[T])(implicit ordering: Ordering[T]): List[T] = {
    // not efficient, but fun
    list.foldLeft(List[T]()) { (x, y) =>
      val (sorted, xs) = x.span(x => ordering.lt(x, y))
      sorted ::: y :: xs
    }
  }

  def elementExists[T](l: List[T], e: T): Boolean = {
    // built in
    l.contains(e)
  }

  def oddElements(iList: List[Int]): List[Int] = {
    iList.filter(e => e % 2 == 1)
  }

  def tails[T](l: List[T]): List[List[T]] = {
    if (l.isEmpty) List(Nil)
    else l :: tails(l.tail)
  }
}
