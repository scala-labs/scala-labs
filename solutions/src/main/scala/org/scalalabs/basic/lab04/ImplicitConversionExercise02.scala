package org.scalalabs.basic.lab04

import org.joda.time.{ Duration, DateTime }
import scala.math._
import language.implicitConversions
import language.higherKinds
import org.json4s._
import org.json4s.JsonDSL._
import scala.util.control._

object ImplicitConversionExercises02 {

  case class Euro(val euros: Int, val cents: Int) {
    lazy val inCents: Int = euros * 100 + cents
  }

  object Euro {
    def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)

    implicit object EuroAsJsonConverter extends JsonConverter[Euro] {
      override def toJSON(e: Euro): JValue = EuroJsonMarshallerHelper.marshal(e)
      override def fromJson(json: JValue): Euro = EuroJsonMarshallerHelper.unmarshal(json)
    }
  }

  object EuroJsonMarshallerHelper {
    implicit val formats = DefaultFormats
    def marshal(e: Euro): JValue = ("symbol" -> "EUR") ~ ("amount" -> s"${e.euros},${e.cents}")
    def unmarshal(json: JValue): Euro = {
      Exception.allCatch.opt {
        val amount = (json \ "amount").extract[String].split(",")
        Euro(amount(0).toInt, amount(1).toInt)
      } getOrElse (Euro(0, 0))
    }
  }

  import annotation.implicitNotFound
  @implicitNotFound(msg = "Cannot find JsonParser type class for ${T}")
  trait JsonConverter[T] {
    def toJSON(t: T): JValue
    def fromJson(json: JValue): T
  }

  /**
   * =======================================================
   */
  object Exercise01 {

    class EuroBuilder(val amount: Int, val inCents: Int) {
      def euros = new EuroBuilder(0, inCents + amount * 100)
      def cents = new EuroBuilder(0, inCents + amount)
      def apply(amount: Int) = new EuroBuilder(amount, inCents)
    }

    implicit def fromEuroBuilder(eb: EuroBuilder): Euro = Euro.fromCents(eb.inCents)
    implicit def fromInt(value: Int): EuroBuilder = new EuroBuilder(value, 0)
  }

  /**
   * =======================================================
   */
  object Exercise02 {
    implicit object OrderedEuro extends Ordering[Euro] {
      def compare(x: Euro, y: Euro): Int = x.inCents - y.inCents
    }
  }

  /**
   * =======================================================
   */
  object Exercise03 {

    object JsonConverter {
      def convertToJson[T: JsonConverter](t: T): JValue = {
        implicitly[JsonConverter[T]].toJSON(t)
      }
      def parseFromJson[T: JsonConverter](json: JValue): T = {
        implicitly[JsonConverter[T]].fromJson(json)
      }
    }
  }
}
