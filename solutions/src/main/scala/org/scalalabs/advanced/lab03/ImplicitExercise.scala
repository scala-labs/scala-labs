package org.scalalabs.advanced.lab03
import sys._
import scala.language.higherKinds
import scala.language.implicitConversions
/**
 * An very simple implementation of trait representing anything that can be compared.
 * In Java this is similar to the Comparator interface.
 *
 * In the Scala libraries, a far more complete (and thus more complex) version is the scala.math.Ordering trait.
 */
trait Ord[A] {
  self =>
  def compare(x: A, y: A): Int

  def max[T](xs: List[T])(implicit ord: Ord[T]): T = xs reduceLeft ((x, y) => if (ord.compare(x, y) < 0) y else x)

  def min[T](xs: List[T])(implicit ord: Ord[T]): T = xs reduceLeft ((x, y) => if (ord.compare(x, y) < 0) x else y)

  def minFor[T](xs: List[T], f: T => A)(implicit ord: Ord[A]): T =
    xs reduceLeft ((x, y) => if (ord.compare(f(x), f(y)) < 0) x else y)

  def maxFor[T](xs: List[T], f: T => A)(implicit ord: Ord[A]): T =
    xs reduceLeft ((x, y) => if (ord.compare(f(x), f(y)) < 0) y else x)

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
    if (l.isEmpty) error("bzzt.. max on empty list")

    l.reduceLeft((x, y) => if (o.compare(x, y) > 0) x else y)
  }

  def mymin[B >: A](implicit o: Ord[B]): A = {
    if (l.isEmpty) error("bzzt.. min on empty list")

    l.reduceLeft((x, y) => if (o.compare(x, y) > 0) y else x)
  }
}

trait AddableList[A] {
  val value: List[A]
  def add(implicit m: Monoid[A]): A = value.foldLeft(m.empty)(m.append)
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

  implicit def toAddableList[A](xs: List[A]) = new AddableList[A] { val value = xs }

  implicit def toPimpedList[A](xs: List[A]) = new PimpedList[A] { val l = xs }

  def add[T](xs: List[T])(implicit m: Monoid[T]): T = if (xs.isEmpty) m.empty else m.append(xs.head, add(xs.tail))

  def add[A](ns: A*)(implicit n: Numeric[A]) = {
    ns reduceLeft (n plus (_, _))
  }

}

object Monads {

  /**
   * A simplified version of Scala's Option class (here named Maybe after the Haskell type) representing either one value, or None.
   */
  class Maybe[+T]
  case class Just[T](value: T) extends Maybe[T]
  case object None extends Maybe[Nothing]

  //  implicit def maybeToMonad[A, B](a: Maybe[A])(implicit m: Monad[Maybe]) = new {
  //   def bind[B](f: A => Maybe[B]): Maybe[B] = m bind (a, f)
  // }
  //
  //  implicit def listToMonad[A, B](a: List[A])(implicit m: Monad[List]) = new {
  //    def bind[B](f: A => List[B]) = m bind (a, f)
  //  }

  /**
   * Defines the inject function for the specified container.
   */
  def inject[M[_], A](a: A)(implicit m: Monad[M]): M[A] = m inject a

  def just[A](a: A): Maybe[A] = Just(a)
  def none = None

  /**
   * A Monad is a Container that has the following operations:
   * - an inject function, that puts a simple value into the container and returns it
   * - a chaining operation, where the result of the computation on the parameter on the left is returned into a
   *  new container with the result parameter of the function.
   *
   *
   * A Monad should have a 'type constructor'. This means in normal terms, that it is parametrized with a type.
   * So the Container should contain Integers, or Strings, or anything else that is specified by the type constructor.
   *
   * Scala contains many classes that are Monads. An example is the Scala List class.
   *
   * In fact, any Scala class that has a flatMap method should be a Monad.
   *
   * A simple example is Scala's Option class, that has two subclasses: Some, representing a value, or None.
   * In our example, we use a simplified version named 'Maybe', the name that is used in Haskell.
   *
   */
  trait Monad[C[_]] {
    /**
     * Puts a value in the Container.
     */
    def inject[A](a: A): C[A]

    /**
     * A chaining function, binding the result of the computation of the left to the right.
     * In Scala, this is defined as the flatMap function.
     */
    def bind[A, B](a: C[A], f: A => C[B]): C[B]
  }

  object Monad {
    /**
     * An instance of the Monad for the Maybe type.
     */
    implicit object MaybeMonad extends Monad[Maybe] {
      /**
       * The bind method does the following: in case the value on the left, a: Maybe[A] is Just(something), the function is applied
       * to something. In case it is None, the result is None
       */
      override def bind[A, B](a: Maybe[A], f: A => Maybe[B]): Maybe[B] = a match {
        case Just(x) => f(x)
        case None => None
      }

      /**
       * The inject function just returns a Just(a).
       */
      override def inject[A](a: A): Maybe[A] = Just(a)
    }

    implicit object ListMonad extends Monad[List] {
      /**
       * bind is just the same as the flatmap method on the list.
       */
      override def bind[A, B](a: List[A], f: A => List[B]): List[B] = a flatMap (f)

      /**
       * The inject uses the List object to create a list with one value.
       */
      override def inject[A](a: A): List[A] = List(a)
    }
  }

  trait MA[M[_], A] {
    val value: M[A]

    def bind[B](f: A => M[B])(implicit m: Monad[M]) = m bind (value, f)
  }
}
