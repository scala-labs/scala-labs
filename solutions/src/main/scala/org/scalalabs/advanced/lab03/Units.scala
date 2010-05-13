package org.scalalabs.advanced.lab03

import org.scalalabs.advanced.lab03.ChurchEncoding._

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

}