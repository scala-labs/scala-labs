package org.scalalabs.advanced.lab03

import org.scalalabs.advanced.lab03.ChurchEncoding._
import java.lang.Math.PI

/**
 * User: arjan
 * Date: May 13, 2010
 * Time: 9:27:38 AM
 */

object Units {
  case class Meter[M <: CNum](value: Double) {
    def +(that: Meter[M]) = Meter[M](this.value + that.value)
    def -(that: Meter[M]) = Meter[M](this.value - that.value)
    def *[M2 <: CNum](that: Meter[M2]) = Meter[M + M2](this.value * that.value)
    def /[M2 <: CNum](that: Meter[M2]) = Meter[M - M2](this.value / that.value)
  }

  type Length = Meter[one]
  type Area = Meter[two]
  type Volume = Meter[three]

  val x = new Length(2)
  val y = new Length(3)
  val z = new Length(4)

  val a: Area = x * y

  def squareArea(x:Length):Area = x * x

//  def circleArea(r:Length):Area = r * r * PI
  
}