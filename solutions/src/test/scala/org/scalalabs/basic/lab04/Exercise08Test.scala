package org.scalalabs.basic.lab04

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._
import Exercise08._
import org.joda.time.Duration

/**
 * @see Exercise08
 */

class Exercise08Test {

  @Test
  def celsiusToFahrenheitConverter = {
    val c = new Celsius(10)
    val f = new Fahrenheit(30)
    assertEquals("It's 10.0 degree celsius", TemperaturPrinter.printCelsius(c))
    assertEquals("It's -1.11 degree celsius", TemperaturPrinter.printCelsius(f))
    assertEquals("It's 50.0 fahrenheit", TemperaturPrinter.printFahrenheit(c))
    assertEquals("It's 30.0 fahrenheit", TemperaturPrinter.printFahrenheit(f))
  }


  @Test
  def convertStringToList = {
     assert(List('H', 'e', 'l', 'l', 'o') == Exercise08.stringToList("Hello"))
  }

  @Test
  def durationDsl = {
    import TimeUtils._
    println(1 days)
    println(((1 days) + (2 hours)))
    assert((1 days).millis == new Duration(24L * 60L * 60L * 1000L).getMillis())
    assert((1.days + 2.hours).millis == new Duration(26L * 60L * 60L * 1000L).getMillis())
  }
}