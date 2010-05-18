package org.scalalabs.advanced.lab03

import org.scalalabs.advanced.lab03.Monoid


/**
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 1:52:26 PM
 */
/**
 * An very simple implementation of trait representing anything that can be compared.
 * In Java this is similar to the Comparator interface.
 *
 * In the Scala libraries, a far more complete (and thus more complex) version is the scala.math.Ordering trait. 
 */
trait Ord[A] {
  self =>
  def compare(x: A, y: A): Int
  
  def max[T](xs: List[T])(implicit ord: Ord[T]): T = xs reduceLeft((x,y) => if (ord.compare(x, y) < 0) y else x)

  def min[T](xs: List[T])(implicit ord: Ord[T]): T = xs reduceLeft((x,y) => if (ord.compare(x, y) < 0) x else y)

  def minFor[T](xs: List[T], f: T => A)(implicit ord: Ord[A]): T =
    xs reduceLeft((x,y) => if (ord.compare(f(x), f(y)) < 0) x else y)

  def maxFor[T](xs: List[T], f: T => A)(implicit ord: Ord[A]): T =
    xs reduceLeft((x,y) => if (ord.compare(f(x), f(y)) < 0) y else x)

  def on[T](f: T => A): Ord[T] = new Ord[T] {
    def compare(x: T, y: T) = self.compare(f(x), f(y))
  }
}


object Ord {

  def apply[A](implicit ord: Ord[A]) = ord

  implicit def stringOrd = new Ord[String] {
    override def compare(x: String, y: String) = x.compareTo(y)
  }

  implicit def intOrd = new Ord[Int] {
    override def compare(x: Int, y: Int) = if (x < y) -1 else if (x > y) +1 else 0
  }
 
  implicit def userOrdByName = new Ord[User] {
    override def compare(x: User, y: User) = x.name.compareTo(y.name) 
  }

}

case class User(val name: String, val age: Int)

trait PimpedList[A] {
   val l: List[A]


  def mymax[B >: A](implicit o: Ord[B]): A = {
    if (l isEmpty) error ("bzzt.. max on empty list")

    l.reduceLeft((x, y) => if (o.compare(x, y) > 0) x else y)
  }

  def mymin[B >: A](implicit o: Ord[B]): A = {
    if (l isEmpty) error ("bzzt.. min on empty list")

    l.reduceLeft((x, y) => if (o.compare(x, y) > 0) y else x)
  }
}

trait AddableList[A] {
  val value: List[A]
  def add(implicit m: Monoid[A]): A = value.foldLeft(m empty)(m append)
}

trait Monoid[T] {
  def append(x: T, y: T): T

  def empty: T
}


object Monoid {

  implicit object stringMonoid extends Monoid[String] {
    override def append(x: String, y: String): String = x.concat(y)

    override def empty = ""
  }

  implicit object intMonoid extends Monoid[Int] {
    override def append(x: Int, y: Int): Int = x + y

    override def empty = 0
  }

  

}


object ImplicitExercise {

  implicit def toAddableList[A](xs: List[A]) = new AddableList[A]{val value = xs}

  implicit def toPimpedList[A](xs: List[A]) = new PimpedList[A]{val l = xs}

  def add[T](xs: List[T])(implicit m: Monoid[T]): T = if(xs.isEmpty) m empty else m append(xs.head, add(xs.tail))

  def add[A](ns: A*)(implicit n: Numeric[A]) = {
    ns reduceLeft(n plus(_,_))    
  }

}

