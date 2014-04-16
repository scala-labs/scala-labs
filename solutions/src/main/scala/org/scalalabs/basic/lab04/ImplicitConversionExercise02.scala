package org.scalalabs.basic.lab04

import org.joda.time.{ Duration, DateTime }
import scala.math._
import language.implicitConversions
import language.higherKinds
import scala.util.parsing.json.JSONObject
import scala.util.control._

object ImplicitConversionExercises02 {

  case class Euro(val euros: Int, val cents: Int) {
    lazy val inCents: Int = euros * 100 + cents
  }

  object Euro {
    def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)

    implicit object EuroAsJsonConverter extends JsonConverter[Euro] {
      override def toJSON(e: Euro): JSONObject = JSONObject(Map("symbol" -> "EUR", "amount" -> s"${e.euros},${e.cents}"))
      override def fromJson(json: JSONObject): Euro = {
        val opt = for {
          eurosOpt <- json.obj.get("amount")
          amount = eurosOpt.toString.split(",")
          (euros, cents) <- Exception.allCatch.opt(amount(0).toInt -> amount(1).toInt)
        } yield Euro(euros, cents)
        opt.getOrElse(Euro(0, 0))
      }
    }
  }

  import annotation.implicitNotFound
  @implicitNotFound(msg = "Cannot find JsonParser type class for ${T}")
  trait JsonConverter[T] {
    def toJSON(t: T): JSONObject
    def fromJson(json: JSONObject): T
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
      def convertToJson[T: JsonConverter](t: T): JSONObject = {
        implicitly[JsonConverter[T]].toJSON(t)
      }
      def parseFromJson[T: JsonConverter](json: JSONObject): T = {
        implicitly[JsonConverter[T]].fromJson(json)
      }
    }
  }
}
