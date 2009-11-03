package org.scalalabs.basic.lab04

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._
import Exercise08._
import org.joda.time.Duration

/**
 * Created by IntelliJ IDEA.
 * User: arjan
 * Date: Oct 31, 2009
 * Time: 8:19:07 AM
 * To change this template use File | Settings | File Templates.
 */

class Exercise08Test {

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