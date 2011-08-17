package org.scalalabs.basic.lab04

import org.junit.Test
import org.junit.Assert._
import ImplicitConversionExercise._
import org.joda.time.Duration

/**
 * @see ImplicitConversionExercise
 */
class ImplicitConversionExerciseTest {
  @Test
  def convertStringToList = {
    assert(List('H', 'e', 'l', 'l', 'o') == ImplicitConversionExercise.stringToList("Hello"))
  }

  @Test
  def celsiusToFahrenheitConverter = {
    fail("uncomment and fix this test")
    // TODO uncomment this test and let it work using some implicit magic to convert Celsius objects to Fahrenheit objects.
    // val c = new Celsius(10)
    // val f = new Fahrenheit(30)
    // assertEquals("It's 10.0 degree celsius", TemperaturePrinter.printCelsius(c))
    // assertEquals("It's -1.11 degree celsius", TemperaturePrinter.printCelsius(f))
    // assertEquals("It's 50.0 fahrenheit", TemperaturePrinter.printFahrenheit(c))
    // assertEquals("It's 30.0 fahrenheit", TemperaturePrinter.printFahrenheit(f))
  }

  @Test
  def addMethodToString = {
    // TODO uncomment this test and let it work using some implicit magic in object ImplicitConversionExercise
    // assertEquals("camelCaseMe", "camel case me".camelCase)
  }

  @Test
  def durationDsl = {
    fail("uncomment and fix this test")
    // TODO uncomment this test and let it work using some implicit magic in object TimeUtils
    // import TimeUtils._
    // println(1 days)
    // println(((1 days) + (2 hours)))
    // assert((1 days).millis == new Duration(24L * 60L * 60L * 1000L).getMillis())
    // assert((1.days + 2.hours).millis == new Duration(26L * 60L * 60L * 1000L).getMillis())
  }
}
