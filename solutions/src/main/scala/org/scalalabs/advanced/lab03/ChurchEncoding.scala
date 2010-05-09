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

  trait CNum {type succ <: CNum}
  trait next[P <: CNum] extends CNum{type succ=next[next[P]]}
  abstract class zero extends CNum {type succ = next[zero]}

  type one=zero#succ
  type two=one#succ

  case class Equals[A >: B <: B, B]()

}
