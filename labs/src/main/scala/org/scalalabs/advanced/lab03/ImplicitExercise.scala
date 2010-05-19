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
   * This method assumes an implicit variable, to be in scope, that must be an instance of the Monoid trait.
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
  /**
   * The append is the operation dat adds two parameters, resulting in a parameter of the same type.
   * For example, in case of an Int, this will be the addition method (+).
   */
  def append(x: T, y: T): T

  /**
   * Defines the empty, or zero, value for the designated type. 
   */
  def empty: T
}

/**
 * This object defines the main implicits that should be in scope for our unit tests to work.
 * The implicit variables (objects, in this case) in this module are in scope because this is a companion module of the Monoid trait.
 * They will be used whenever an implicit variable that has the Monoid type in a method call is used. 
 */
object Monoid {
  /**
   * The implicit variables object should implement the Monoid trait for a String.
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

  //TODO implement implicit conversion for list to trait AddableList defined above.
  //If this implicit is defined, we can call list.add, as if it was a normal method on the List class.
  //Note that we use list.add here, instead of the more normal list.sum, because the latter is already
  //defined on the list class itself.

  /**
   * Defines an add method that takes a list as an explicit argument.
   * As you see, there is also an implicit variable of the type Monoid defined.
   * This assumes that there is an implicit variable in scope thath has this type.
   *
   * If no such variable is in scope, compilation will fail.
   */
  def add[T](xs: List[T])(implicit m: Monoid[T]): T = if(xs.isEmpty) m empty else m append(xs.head, add(xs.tail))

}

object Monads {

  /**
   * A simplified version of Scala's Option class (here named Maybe after the Haskell type) representing either one value, or None.
   */
  class Maybe[+T]
  case class Just[T](value: T) extends Maybe[T]
  case object None extends Maybe[Nothing]


  /**
   * TODO
   * implement the implicit conversion from a Maybe type to the Monad.
   * You should implement the bind function, and also add the Monad as implicit parameter here.
   * The idea is that the client can do the following:
   *
   * println(just(1) bind(v => just(v+1) --> prints(2)
   */
  implicit def maybeToMonad[A, B] = new {
    //TODO bind function here
 }

  /**
   * TODO
   * implement the implicit conversion from a List type to the Monad.
   * You should implement the bind function, and also add the Monad as implicit parameter here.
   * The idea is that the client can do the following:
   *
   * println(List(1) bind (x => List(x+2))) --> prints(List(3))
   */
  implicit def listToMonad[A, B] = new {
    //TODO bind function here
  }

  /**
   * Defines the inject function for the specified container.
   */
  def inject[M[_], A](a: A)(implicit m: Monad[M]): M[A] = m inject a

  /**
   * Convenience methods that constructs Justs and Nones
   */
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
  trait Monad[C[_]]  {
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
   implicit object MaybeMonad /*extends Monad[Maybe]  TODO uncomment and implement the bind and inject methods */ {
     /**
      * The bind method does the following: in case the value on the left, a: Maybe[A] is Just(something), the function is applied
      * to something. In case it is None, the result is None
      */
     def bind = {}
     /**
      * The inject function just returns a Just(a).
      */
     def inject = {}
   }

   implicit object ListMonad /*extends Monad[List] TODO uncomment and implement the bind and inject methods*/ {
     /**
      * bind is just the same as the flatmap method on the list.
      */
     def bind = {}

     /**
      * The inject uses the List object to create a list with one value.
      */
     def inject = {}
    }
  }

}
