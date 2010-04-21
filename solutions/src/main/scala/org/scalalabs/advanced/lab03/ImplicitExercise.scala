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

trait FoldLeft[Container[_]] {
   def foldLeft[A, B](xs: Container[A], b: B, f: (B, A) => B): B
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

  def add[T](xs: List[T])(implicit m: Monoid[T]): T = if(xs.isEmpty) m empty else m append(xs.head, add(xs.tail))

}