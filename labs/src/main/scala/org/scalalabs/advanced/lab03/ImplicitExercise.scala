package org.scalalabs.advanced.lab03



/**
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 1:52:26 PM
 */
/**
 * An very simple implementation of trait representing anything that can be compared.
 * In Java this is similar to the Comparator interface.
 *
 * In the Scala libraries, a far more complete (and more complex) version is the scala.math.Ordering trait. 
 */
trait Ord[A] {
  self =>
  def compare(x: A, y: A): Int

  /**
   * TODO implement the max method, so that it returns the maximum element of the given list. The elements in the list are supposed to be implementing
   * the Ord trait itself.
   * You can apply the Ord as an implicit parameter to achieve this, so that the method can just be called as max(List(a, b, c)) where the elements
   * of the List can be converted to an Ord by an implicit conversion, defined in the Ord object.
   */
  def max[T](xs: List[T]) = error("implement me")

  /**
   * TODO implement the min method, so that it returns the min element of the given list. The elements in the list are supposed to be implementing
   * the Ord trait itself.
   * You can apply the Ord as an implicit parameter to achieve this, so that the method can just be called as max(List(a, b, c)) where the elements
   * of the List can be converted to an Ord by an implicit conversion, defined in the Ord object.
   */
  def min[T](xs: List[T]) = error("implement me")

  /**
   * TODO implement the minFor method, so that it returns the min element of the given list. The Ordering is defined by the given
   * function f, which takes a list parameter, and returns an element of a possibly different type (denoted by A). The return type of the function f
   * is supposed to be implicitly convertable to the Ord trait.
   * the Ord trait itself. Again, this can be achieved by passing Ord as an implicit parameter to achieve this, and defined the required implicit conversions in the Ord object.
   */
  def minFor[T](xs: List[T], f: T => A) = error("implement me")

  /**
   * TODO implement the minFor method, so that it returns the max element of the given list. The Ordering is defined by the given
   * function f, which takes a list parameter, and returns an element of a possibly different type (denoted by A). The return type of the function f
   * is supposed to be implicitly convertable to the Ord trait.
   * the Ord trait itself. Again, this can be achieved by passing Ord as an implicit parameter to achieve this, and defined the required implicit conversions in the Ord object.
   */
  def maxFor[T](xs: List[T], f: T => A) = error("implement me")
  /**
   * TODO implement the on method, so that it returns the and Ord that is defined by the given
   * function f, which takes a list parameter, and returns an element of a possibly different type (denoted by A). The return type of the function f
   * is supposed to be implicitly convertable to the Ord trait.
   * the Ord trait itself. Again, this can be achieved by passing Ord as an implicit parameter to achieve this, and defined the required implicit conversions in the Ord object.
   */
  def on[T](f: T => A): Ord[T]  = error("implement me")
}


object Ord {

  /**
   *  Returns an instance of the Ord trai.
   */
  def apply[A](implicit ord: Ord[A]) = ord

  //TODO defining implicit conversions from String and Int classes to the Ord trait here.
  
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

/**
 * A pimped version of a List that supports an 'add' method.
 */
trait AddableList[A] {
  val value: List[A]
  def add(implicit m: Monoid[A]): A = value.foldLeft(m empty)(m append)
}
/**
 * @see http://en.wikipedia.org/wiki/Monoid
 *
 * A Monoid is a simple datastructure that has the following properties:
 *
 * - it has an append operation appends two elements, giving a resulting element of the same type
 *
 * - it has an identity element, so that the append operation append(identity: T, x:T) returns x.
 *
 * For example, a String monoid implemtation would yield: append("ab", "cde") = "abcde"
 *  
 */
trait Monoid[T] {
  def append(x: T, y: T): T

  def empty: T
}

object Monoid {
  implicit object stringMonoid //TODO implement

  implicit object intMonoid //TODO implement


  //TODO implement implicit conversion for list to trait AddableList defined above, so that our pimped list now supports an 'add' method.
}

object AddUsingVarargsAndScalaNumeric {
  //TODO implement the add method such that add(1,2,3,4,5) works. The argument should take a type parameter (which it now doesn't), and return that type.
  //In this case, add(1,2,3) should return an Int, but add(1L, 2L, 3L) returns a long. The type in the argument list is now Any, but that should be changed as well.
  //Lastly, you can use (implicitly) the Numeric trait of Scala to implement the addition of the various types. 
    def add(a: Any*) = error("implement me")
}

object ListToPimpedList {
  //TODO implement implicit conversion for list to pimped list trait, so that the various methods in that class are supported. 
}
object ImplicitExercise {


  def add[T](xs: List[T])(implicit m: Monoid[T]): T = if(xs.isEmpty) m empty else m append(xs.head, add(xs.tail))

}

