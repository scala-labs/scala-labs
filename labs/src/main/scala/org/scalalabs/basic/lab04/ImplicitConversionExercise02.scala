package org.scalalabs.basic.lab04

import org.joda.time.{Duration, DateTime}
import scala.math._
import language.implicitConversions
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import scala.util.control._

case class Euro(val euros: Int, val cents: Int) {
  lazy val inCents: Int = euros * 100 + cents
}

object Euro {
  def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)
}

/** Exercise 1: Create a money DSL which allows you to create Euro classes as
  * follows:
  *   - 2 euros => Euro(2, 0)
  *   - 40 cents => Euro(0, 40)
  *   - 2 euros 45 cents => Euro(2,45) The Euro case class is already provided.
  *     Hint: Use an intermediate class (e.g. EuroBuilder) to create the Euro
  *     object. E.g. 2 euros = 2 -> EuroBuilder Use an implicit conversion from
  *     EuroBuilder -> Euro to get the final result In the EuroBuilder you might
  *     need the apply() method to cover this case: 2 euros >45< cents
  */
object Exercise01 {}

/** Exercise 2: Implement Scala's built in Ordering type class for Euro, so that
  * the call to Seq(Euro(1,5), Euro(3,2)).sorted compiles.
  */
object Exercise02 {}

/** Exercise 3: Implement a type class pattern to convert domain objects to and
  * from json. Take a look at the already defined type class trait @see
  * JsonConverter.
  *   1. Implement the methods of the JsonCoverter object below that converts
  *      domain objects to and from json making use of the JsonConverter type
  *      class trait. 2. Provide an implementation of the JsonConverter type
  *      class trait for the Euro class. Place the implementation in the Euro's
  *      companion object so that the implicit resolution requires no import.
  *      For marshalling and unmarshalling json make use of the @see
  *      EuroJsonMarshallerHelper
  */
object Exercise03 {
  object JsonConverter {
    def convertToJson[
      T
      /** provide context bound */
    ](t: T): JValue = {
      ???
    }
    def parseFromJson[
      T
      /** provide context bound */
    ](json: JValue): T = {
      ???
    }
  }

  /** Only used for Exercise03!
    */
  trait JsonConverter[T] {
    def toJSON(t: T): JValue
    def fromJson(json: JValue): T
  }

  /** Only used for Exercise03!
    */
  object EuroJsonMarshallerHelper {
    implicit val formats: DefaultFormats.type = DefaultFormats
    def marshal(e: Euro): JValue =
      ("symbol" -> "EUR") ~ ("amount" -> s"${e.euros},${e.cents}")
    def unmarshal(json: JValue): Euro = {
      Exception.allCatch.opt {
        val amount = (json \ "amount").extract[String].split(",")
        Euro(amount(0).toInt, amount(1).toInt)
      } getOrElse (Euro(0, 0))
    }
  }
}
