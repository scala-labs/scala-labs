package org.scalalabs.advanced.lab03

/**
 * User: arjan
 * Date: May 8, 2010
 * Time: 8:02:05 AM
 */


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
  trait CBool {type cond[T, F]}

  /**
   * The CFalse trait should refine the type to  F
   */
  trait CFalse extends CBool {/*TODO uncomment and insert type implementation here*/}
  /**
   * The CFalse trait should return the type F
   */
  trait CTrue extends CBool {/*TODO uncomment and insert correct type implementation here*/}

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
  type one=zero#succ

  /**
   * type representing Church number two, the successor of one
   * Can also be written two=zero#succ#succ
   */
  type two=one#succ

  /**
   * type representing Church number three, the successor of two
   * Can also be written two=zero#succ#succ#succ
   */
  type three=two#succ

  /**
   * Type that represents addition. It should be so, that one plus one == two
   */
  type plus[N1<:CNum, N2<:CNum] = N1#add[N2]

/**
 *  A class that can be used to check whether two types are equivalent.
 * It only compiles when the types that are passed are exactly equal.
 */
  case class Equals[A >: B <: B, B]()

}