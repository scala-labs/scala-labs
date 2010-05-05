package org.scalalabs.advanced.lab03



/**
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 1:52:26 PM
 */
trait Monoid[T] {
  def append(x: T, y: T): T

  def empty: T
}

//trait FoldLeft[Container[_]] {
//   def foldLeft[A, B](xs: Container[A], b: B, f: (B, A) => B): B
//}

//object FoldLeft {
//  implicit def ListFoldLeft = new FoldLeft[List] {
//    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
//  }
//}
trait Ord[A] {
  def compare(x: A, y: A): Int
  
  def max[T](xs: List[T])(implicit ord: Ord[T]): T = xs reduceLeft((x,y) => if (ord.compare(x, y) < 0) y else x)

  def min[T](xs: List[T])(implicit ord: Ord[T]): T = xs reduceLeft((x,y) => if (ord.compare(x, y) < 0) x else y)

//  def naturalOrder[T](xs: List[T])(implicit ord: Ord[T]) : List[T] =  xs
}


object Ord {

  def apply[A](implicit ord: Ord[A]) = ord

  implicit def stringOrd = new Ord[String] {
    override def compare(x: String, y: String) = x.compareTo(y)
  }

  implicit def intOrd = new Ord[Int] {
    override def compare(x: Int, y: Int) = if (x < y) -1 else if (x > y) +1 else 0
  }

//  implicit def listOrd = new Ord[List[Ord[_]]] {
//    override def compare(xs: List[_], y: Int) = if (x < y) -1 else if (x > y) +1 else 0
//  }


  implicit def personOrdByName = new Ord[User] {
    override def compare(x: User, y: User) = x.name.compareTo(y.name) 
  }

}

case class User(val name: String, val age: Int)

trait AddableList[A] {
  val value: List[A]
  def add(implicit m: Monoid[A]): A = value.foldLeft(m empty)(m append)
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

  def add[T](xs: List[T])(implicit m: Monoid[T]): T = if(xs.isEmpty) m empty else m append(xs.head, add(xs.tail))

  def add[A](ns: A*)(implicit n: Numeric[A]) = {
    ns reduceLeft(n plus(_,_))    
  }

}

