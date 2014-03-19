package org.scalalabs.basic.lab04

import org.joda.time.{ Duration, DateTime }
import scala.math._
import language.implicitConversions
import language.higherKinds

case class Euro(val euros: Int, val cents: Int)

object Euro {
  def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)
}

/**
 * Exercise 2:
 * Create a money DSL which allows you to create Euro classes as follows:
 * - 2 euros           => Euro(2, 0)
 * - 40 cents          => Euro(0, 40)
 * - 2 euros 45 cents  => Euro(2,45)
 * The Euro case class is already provided.
 * Hint: Use an intermediate class (e.g. EuroBuilder) to create the Euro object.
 * E.g. 2 euros = 2 -> EuroBuilder
 * Use an implicit conversion from EuroBuilder -> Euro to get the final result
 * In the EuroBuilder you might need the apply() method to cover this case:
 * 2 euros >45< cents
 */
object Exercise01 {

}


