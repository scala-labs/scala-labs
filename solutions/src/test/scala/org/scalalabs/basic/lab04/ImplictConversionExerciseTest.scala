package org.scalalabs.basic.lab04

import ImplictConversionExercise02._
import ImplictConversionExercise03._
import ImplictConversionExercise04._
import ImplictConversionExercise05._
import org.joda.time.Duration

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.joda.time._
/**
 * @see ImplictConversionExercise
 */
@RunWith(classOf[JUnitRunner])
class ImplictConversionExerciseTest extends Specification with DeactivatedTimeConversions {

  "ImplictConversionExercise01" should {
    "convert string to list" in {
      List('H', 'e', 'l', 'l', 'o') ==== ImplictConversionExercise01.stringToList("Hello")
    }
  }

  "ImplictConversionExercise02" should {
    "convert celsius to fahrenheit" in {
      val c = new Celsius(10)
      val f = new Fahrenheit(30)
      "It's 10.0 degree celsius" ==== TemperaturPrinter.printCelsius(c)
      "It's -1.11 degree celsius" ==== TemperaturPrinter.printCelsius(f)
      "It's 50.0 fahrenheit" ==== TemperaturPrinter.printFahrenheit(c)
      "It's 30.0 fahrenheit" ==== TemperaturPrinter.printFahrenheit(f)
    }
  }

  "ImplictConversionExercise03" should {
    "add camelCase method to String" in {
      "camelCaseMe" ==== "camel case me".camelCase
    }
  }

  "ImplictConversionExercise04" should {
    "have a working time DSL" in {
      import TimeUtils._
      println(1 days)
      println((1 days) + (2 hours))
      (1 days).millis ==== new Duration(24L * 60L * 60L * 1000L).getMillis()
      (1.days + 2.hours).millis ==== new Duration(26L * 60L * 60L * 1000L).getMillis()
    }
  }

  "ImplictConversionExercise05" should {
    "have a working money DSL" in {
      Euro(2, 0) must be_==~(2 euros)
      Euro(0, 25) must be_==~(25 cents)
      Euro(2, 25) must be_==~(2 euros 25 cents)
    }
  }
}

trait DeactivatedTimeConversions extends org.specs2.time.TimeConversions {
  override def intToRichLong(v: Int) = super.intToRichLong(v)
} 