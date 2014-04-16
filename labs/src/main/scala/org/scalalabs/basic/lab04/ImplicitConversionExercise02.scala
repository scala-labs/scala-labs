package org.scalalabs.basic.lab04

import org.joda.time.{ Duration, DateTime }
import scala.math._
import language.implicitConversions
import language.higherKinds
import scala.util.parsing.json.JSONObject

case class Euro(val euros: Int, val cents: Int) {
  lazy val inCents: Int = euros * 100 + cents
}

object Euro {
  def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)
}

/**
 * Only used for exercise2!
 */
trait JsonConverter[T] {
  def toJSON(t: T): JSONObject
  def fromJson(json: JSONObject): T
}
/**
 * Exercise 1:
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

/**
 * Exercise 2:
 * Implement Scala's built in Ordering type class for Euro, 
 * so that the call to Seq(Euro(1,5), Euro(3,2)).sorted compiles.
 */
object Exercise03 {

}

/**
 * Exercise 3:
 * Implement a type class pattern to convert domain objects to and from json.
 * Take a look at the already defined type class trait @see JsonConverter.
 * 1. Implement the methods of the JsonCoverter object below that converts domain objects to and from json making use of the JsonConverter type class trait.
 * 2. Provide an implementation of the JsonConverter type class trait for the Euro class.
 * Place the implementation in the Euro's companion object so that the implicit resolution requires no import.
 */
object Exercise02 {
  object JsonConverter {
    def convertToJson[T /**provide context bound*/ ](t: T): JSONObject = {
      ???
    }
    def parseFromJson[T /**provide context bound*/ ](json: JSONObject): T = {
      ???
    }
  }

}


