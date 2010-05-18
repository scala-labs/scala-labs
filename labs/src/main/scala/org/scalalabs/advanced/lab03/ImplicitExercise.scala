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
   *  Returns an instance of the Ord trait.
   */
  def apply[A](implicit ord: Ord[A]) = ord

  //TODO defining implicit conversions from String and Int classes to the Ord trait here.
  
}

case class User(val name: String, val age: Int)

/**
 * A general Pimped list class, defining extra methods for a List.
 */
trait PimpedList[A] {
   val l: List[A]

  /**
   * A 'mymax', instead of the 'normal' max defined on a list, that determines the maximum element based
   * on a given Ord.
   * Note that this method is supposed to be called like this from the client side:
   * List(foo, bar, baz) mymax //yields foo
   *
   * The elements of the List should be instances of the Ord class, therefore, and should be defined implicitly.
   * The implicit conversion to the Ord should also be in scope in order to compile this correctly.
   */
  def mymax /*TODO pass on type parameters and implicit parameters here*/: A = error("TODO implement me")
  /**
   * A 'mymin', instead of the 'normal' min defined on a list, that determines the minimum element based
   * on a given Ord.
   * Note that this method is supposed to be called like this from the client side:
   * List(foo, bar, baz) mymax //yields foo
   *
   * The elements of the List should be instances of the Ord class, therefore, and should be defined implicitly.
   * The implicit conversion to the Ord should also be in scope in order to compile this correctly.
   */
  def mymin /*TODO pass on type parameters and implicit parameters here*/: A = error("TODO implement me")

}

/**
 *  A pimped version of a List that supports an 'add' method.
 * This is a list that is converted from a 'normal' list using an implicit conversion.
 */
trait AddableList[A] {
  /**
   * The initial value of this list, representing just the original list.
   */
  val value: List[A]

  /**
   * The 'pimped' add method, that can now be called on the List class, as if it were a normal method on that class.
   * This method assumes another implicit variable, or object, to be in scope: the Monoid trait.
   * All elements of our AddableList should therefore be implicitly convertable to a Monoid trait.
   * The implicit conversions have been defined (if all is well) in the Monoid object, and will be in scope if our unit test runs.
   * If such a required conversion is not in scope, the client calling our little API would not compile. 
   *
   */
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
 * For example, a String monoid implementation would yield: append("ab", "cde") = "abcde"
 *  
 */
trait Monoid[T] {
  def append(x: T, y: T): T

  def empty: T
}

/**
 * This object defines the main implicits that should be in scope for our unit tests to work.
 * The implicit definitions (objects, in this case) in this module are in scope because this is a companion module of the Monoid trait.
 */
object Monoid {
  /**
   * This implicit object should implement the Monoid trait for a String.
   * Implement the appropriate methods for append and empty that are suitable for Strings.
   * This object is used in various unit tests that use the 'add' method.
   *
   * Note that this object, and the implicit conversion it defines, will be in scope when the ImplicitExercise._ is imported,
   * because it is the companion module of the Monoid trait. 
   */
  implicit object stringMonoid //TODO implement the Monoid trait for Strings

  /**
   * This implicit object should implement the Monoid trait for an Int.
   * Implement the appropriate methods for append and empty that are suitable for Strings.
   * This object is used in various unit tests that use the 'add' method.
   */
  implicit object intMonoid //TODO implement the Monoid trait for Ints


  //TODO implement implicit conversion for list to trait AddableList defined above.
  //If this implicit is defined, we can call list.add, as if it was a normal method on the List class.
  //Note that we use list.add here, instead of the more normal list.sum, because the latter is already
  //defined on the list class itself.
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

  /**
   * Defines an add method that takes a list as an explicit argument.
   * As you see, there is also an implicit argument Monoid defined.
   * This assumes that there is an implicit converion from the List element of type T to the
   * Monoid trait defined above in scope.
   *
   * If no such conversion is in scope, compilation will fail.
   */
  def add[T](xs: List[T])(implicit m: Monoid[T]): T = if(xs.isEmpty) m empty else m append(xs.head, add(xs.tail))

}
