package org.scalalabs.basic.lab04

import org.joda.time.{ Duration, DateTime }
import scala.math._

/**
 * This excersice introduces you to Scala implicit conversion features.
 *
 * Scala has a nice feature that automatically lets you convert types and add methods to an existing class.
 * For instance, it is possible to write "Hello".toList, which yields List(H, e, l, l, o) even though
 * the implementation of the String class does not provide a toList method.
 * This is coined 'library pimping' and is achieved via implicit conversions.
 * In this exercise, you will among other try out some implicit conversions from integers to Joda's DateTime,
 * so we can write little DSL like statements like 1 day + 2 hours.
 *
 * Provide a suitable implementation in order to make the corresponding unittest work.
 *
 * Reference material to solve these exercises can be found here:
 * Implicit conversions: http://programming-scala.labs.oreilly.com/ch08.html#Implicits
 *
 */

object ImplictConversionExercise01 {

  def stringToList(s: String): List[Char] = {
    //build in: our String will be converted to Scala's RichString, because this is defined a Scala
    //object called Predef. This is imported by the compiler by default.
    s.toList
  }
}
/**============================================================================ */
object ImplictConversionExercise02 {

  class Celsius(val degree: Double)
  class Fahrenheit(val fahrenheit: Double)

  object TemperaturPrinter {
    def printCelsius(c: Celsius): String = {
      "It's " + c.degree + " degree celsius"
    }

    def printFahrenheit(f: Fahrenheit): String = {
      "It's " + f.fahrenheit + " fahrenheit"
    }
  }

  /**
   * Use this conversion helper to convert fahrenheit to degree celsius and vice versa in
   * the implicit function you will define.
   */
  object ConversionHelper {
    def fahrenheit2CelsiusConversion(fahrenheit: Double) = {
      val converted = (fahrenheit - 32) / 1.8
      round(converted * 100).toDouble / 100
    }

    def celsius2FahrenheitConversion(degreeCelsius: Double) = {
      degreeCelsius * 1.8 + 32
    }
  }

  implicit def celsiusToFahrenheit(f: Fahrenheit) = { new Celsius(ConversionHelper.fahrenheit2CelsiusConversion(f.fahrenheit)) }

  implicit def fahrenheitToCelsius(c: Celsius) = { new Fahrenheit(ConversionHelper.celsius2FahrenheitConversion(c.degree)) }
}

/**============================================================================ */
object ImplictConversionExercise03 {

  /**
   * Use an implict conversion from string to an anonymous object that contains a camelCase method.
   *  By doing so, the camelCase method is added to the string
   */
  implicit class CamelCaseString(s: String) {
    def camelCase: String = {
      def camelCase(s: String): String = {
        val spaceLetterAndRestOfTextSeqRegExp = """\s(.?)(.*)""".r
        s.span(!_.isSpaceChar) match {
          case (all, "") => all
          case (head, spaceLetterAndRestOfTextSeqRegExp(firstLetter, restOfText)) => head + camelCase(firstLetter.toUpperCase + restOfText)
        }
      }
      camelCase(s)
    }
  }
}

/**============================================================================ */
object ImplictConversionExercise04 {
  object TimeUtils {
    case class DurationBuilder(timeSpan: Long) {
      def now = new DateTime().getMillis()

      def seconds = RichDuration(TimeUtils seconds (timeSpan))

      def minutes = RichDuration(TimeUtils minutes (timeSpan))

      def hours = RichDuration(TimeUtils hours (timeSpan))

      def days = RichDuration(TimeUtils days (timeSpan))

    }

    implicit def longToDuration(l: Long): Duration = new Duration(l)

    implicit def intToDurationBuilder(i: Int): DurationBuilder = new DurationBuilder(i)

    def seconds(in: Long) = in * 1000L

    def minutes(in: Long) = seconds(in) * 60L

    def hours(in: Long) = minutes(in) * 60L

    def days(in: Long) = hours(in) * 24L
  }

  case class RichDuration(val duration: Duration) {
    def millis = duration.getMillis()

    def afterNow = new DateTime().plus(duration)

    def +(that: RichDuration) = RichDuration(this.duration.plus(that.duration))
  }
}

object ImplictConversionExercise05 {
  case class Euro(val euros: Int, val cents: Int) 
  
  object Euro {
    def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)
  }

  class EuroBuilder(val amount: Int, val inCents: Int) {
    private val stateCents = "cents"
    def euros = new EuroBuilder(0, inCents + amount * 100)
    def cents = new EuroBuilder(0, inCents + amount)
    def apply(amount: Int) = new EuroBuilder(amount, inCents)
  }

  implicit def fromEuroBuilder(eb: EuroBuilder):Euro = Euro.fromCents(eb.inCents)
  implicit def fromInt(value: Int):EuroBuilder= new EuroBuilder(value, 0)

}
