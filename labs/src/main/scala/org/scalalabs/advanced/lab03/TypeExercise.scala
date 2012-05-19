package org.scalalabs.advanced.lab03

import sys._

/**
 * An example of using type-level programming in scala.
 *
 * Useful reference: http://apocalisp.wordpress.com/2010/06/08/type-level-programming-in-scala/
 *
 */
object FoodExercise {

  /**
   * Types of food that are commonly eaten.
   */
  trait Food { def name: String }
  object Grass extends Food { def name = "Grass" }
  object Beef extends Food { def name = "Beef" }
  object Fish extends Food { def name = "Fish" }
  object Pizza extends Food { def name = "Beef" }

  /**
   * The Mamal trait should be implemented so that the joinDinnerWith method only compiles
   * if two mamals eat exactly the same type of foo.d
   */
  trait Mamal { self =>
    val eats: Food

    /**
     * TODO
     * Inmplement this is such a way using clever type parameters and implicits such that
     * a Mamal can only join dinner with another mamal that eats exactly the same type of Food,
     * i.e. Mamal1.eats.type == Mamal2.eats.type
     * the method should be callable as follows:
     * mamal1.joinDinnerWith(mamal2)
     *
     */
    def joinDinnerWith(other: Any /*TODO Any is not the right type here, add type parameters, and possibly some clever implicits.*/ ) = None //TODO implement me

    def prefers = "Eating " + eats.name
  }
}

/**
 * A not so useful, but fun if you like it, exercise in encoding Church natural numbers using Scala types.
 * This is implemented using type aliases. The idea is that a trait can define an abstract type alias,
 * that can than be refined in subtraits or classes. In this way, one can define type-safe systems.
 */
object ChurchEncoding {

  /**
   * Trait representing a Church Boolean data type.
   * @see http://en.wikipedia.org/wiki/Church_encoding
   *
   * It is possible to define a type to be defined within classes or traits.
   * These types can then be made concrete in a subclass.
   * Not that the type keyword just defines a type alias, which can be used as follows:
   *
   * <code>
   * scala> type stringList = List[String]
   * defined type alias stringList
   *
   * scala> val l: stringList = List()
   * l: stringList = List()
   *
   * scala> val l: stringList = List("a")
   * l: stringList = List(a)
   *
   * scala> val l: stringList = List(1)
   * <console>:6: error: type mismatch;
   * found   : Int(1)
   * required: String
   *      val l: stringList = List(1)
   * </code>
   *
   *
   * The trait CBool has an abstract type named cond, which has type parameters T and F (which stands for true and false, obviously).
   * The idea is to refine these types in the subclasses CFalse and CTrue, so that:
   *
   * <code>val ctrue: CTrue#cond[Int, String] = 10</code>
   * and
   * <code>val cfalse: CFalse#cond[Int, String] = "abcd"</code>
   *
   * both would compile, but
   *
   * <code>val ctrue: CTrue#cond[Int, String] = "abcd"</code>
   *
   * and
   *
   * <code>val cfalse: CFalse#cond[Int, String] = 10</code>
   *
   * would not.
   *
   * Note: The type defined by this trait can be accessed by the following (for example):
   *
   * val bool = CBool#cond[String, Int]
   *
   *
   */
  trait CBool { type cond[T, F] }

  /**
   * The CFalse trait should refine the type to  F
   */
  trait CFalse extends CBool { /*TODO uncomment and insert type implementation here*/ }
  /**
   * The CFalse trait should return the type F
   */
  trait CTrue extends CBool { /*TODO uncomment and insert correct type implementation here*/ }

  /**
   * Trait representing a Church Numeric data type.
   * @see http://en.wikipedia.org/wiki/Church_encoding
   * This can be encoded using Scala types.
   *
   * It is possible to define a type to be defined within classes or traits.
   * These types can then be made concrete in a subclass. Our trait CNum (Church number) currently has two
   * abstract
   */
  trait CNum {
    /**
     * This type represents the successor of this Church number. It should be implemented in subclasses, so that zero#succ = one,
     * one#succ = two, etc.
     *
     */
    type succ <: CNum

    /**
     * type representing addition, so that one#add[one] = two, two#add[one] = three
     */
    type add[N <: CNum] <: CNum
  }

  /**
   * trait representing a positive Church number
   */
  trait CPos extends CNum

  /**
   * trait representing a negative Church number
   */
  trait CNeg extends CNum

  /**
   * A Trait that represents the next church number of the given P, which must be a positive number
   */
  trait CNext[P <: CPos] extends CPos {
    //TODO implement appropriate implementation for types succ, pred, add, min, neg here
  }

  abstract class zero extends CPos with CNeg {
    type succ = CNext[zero]
    type add[N <: CNum] = N
  }

  /**
   * type representing Church number one, which is the successor of zero
   */
  type one = zero#succ

  /**
   * type representing Church number two, the successor of one
   * Can also be written two=zero#succ#succ
   */
  type two = one#succ

  /**
   * type representing Church number three, the successor of two
   * Can also be written two=zero#succ#succ#succ
   */
  type three = two#succ

  /**
   * Type that represents addition. It should be so, that one plus one == two
   */
  type plus[N1 <: CNum, N2 <: CNum] = N1#add[N2]

  /**
   *  A class that can be used to check whether two types are equivalent.
   * It only compiles when the types that are passed are exactly equal.
   */
  case class Equals[A >: B <: B, B]()

}

/**
 * In Scala, just as in Java, generic types are erased at runtime. Scala does provide a way
 * to obtain the type information at compile-time however, via the Manifest class. You can
 * pass a Manifest as an implicit parameter to your method. The compiler than takes care of
 * providing the required information (i.e. you don't have to instantiate it or take care
 * of this in any way yourself).
 *
 * Below is an example of using Manifests to provide stronger type safety.
 */
object ManifestExercise {
  /**
   * The class below provied an example of a Type Safe Registry class. The idea is to be able to do the following:
   *
   * val reg = new TSReg[String, Int]
   * reg.add("a", 1)
   * reg.add("b", 2)
   *
   * safeGet[Int]("a") -> returns Some(1)
   * safeGet[Int]("c") -> returns None
   * safeGet[String]("a") -> returns None
   *
   * so the given type passed to the safeGet method should match the return type of this registry.
   */
  class TSReg[A, B] {
    /**
     * TODO
     * Implement the map. Hint: Manifests can be passed to parameters, and also stored in values.
     * It is therefore possible to store the manifest that is implicitly passed on as parameter (in the implentation of the add method.)
     */
    private var map = Map.empty[A, (Manifest[_], B)]

    /**
     * TODO
     * Implement the add method. You should use the Manifest as an implicit parameter in order to know the
     * runtime type of value B, which we need to know to implement the safeGet method properly.
     */
    def add(k: A, v: B) = { /*TODO implement me */ }

    /**
     * This is the method that should do the type safe get: i.e. it should return Some(value)
     * iff the given type parameter matches, or is a supertype of the value belonging to the key.
     */
    def safeGet[T](key: A): Option[T] = {
      val ov = map.get(key)
      //TODO implement using manifest
      None
    }
  }
}
