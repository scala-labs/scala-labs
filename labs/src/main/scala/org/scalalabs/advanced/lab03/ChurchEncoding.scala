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
   * Note: The type defined by this trait can be accessed by the following (for example):
   *
   * val bool = CBool#cond[String, Int]
   */
  trait CBool {type cond[T, F]}

  /**
   * The CFalse trait should refine the type to  F
   */
  trait CFalse /*extends CBool {TODO insert type implementation here} */
  /**
   * The CFalse trait should return the type F
   */
  trait CTrue extends CBool {type condition[T,F] = T}

  /**
   * Trait representing a Church Numeric data type.
   * @see http://en.wikipedia.org/wiki/Church_encoding
   */
  trait CNum {
     type succ <: CNum
     type pred <: CNum
     type add[N <: CNum] <: CNum
     type min[N <: CNum] <: CNum
     type neg <: CNum
  }

  trait CPos extends CNum
  trait CNeg extends CNum

  trait next[P <: CPos] extends CPos {
     type succ=next[next[P]]
     type pred = P
     type neg = P#neg#pred
     type add[N <: CNum] = P#add[N]#succ
     type min[N <: CNum] = P#min[N]#succ
  }

  class prev[S <: CNeg] extends CNeg {
      type succ = S
      type add[N <: CNum] = S#add[N]#pred
      type pred = prev[prev[S]]
      type min[N <: CNum] = S#min[N]#pred
      type neg = S#neg#succ
  }

  abstract class zero extends CPos with CNeg {
    type succ = next[zero]
    type pred = prev[zero]
    type neg = zero
    type add[N <: CNum] = N
    type min[N <: CNum] = N#neg
  }

  type one=zero#succ
  type two=one#succ
  type three=two#succ

  type plus[N1<:CNum, N2<:CNum] = N1#add[N2]
  type +[N1<:CNum, N2<:CNum] = plus[N1, N2]

  type -[N1<:CNum, N2<:CNum] = N1#min[N2]

  case class Equals[A >: B <: B, B]()

}