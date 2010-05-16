package org.scalalabs.advanced.lab03

/**
 * User: arjan
 * Date: May 8, 2010
 * Time: 8:02:05 AM
 */

object ChurchEncoding {


  trait CBool {type cond[T, F]}
  trait CFalse extends CBool {type cond[T,F] = F}
  trait CTrue extends CBool {type condition[T,F] = T}

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
