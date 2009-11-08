package org.scalalabs.basic.lab04

import org.joda.time.{Duration, DateTime}

/**
 * Created by IntelliJ IDEA.
 * @author arjan
 *
 * Scala has a nice feature that automatically lets you add methods to an existing class.
 * For instance, it is possible to write "Hello".toList, which yields List(H, e, l, l, o)
 * This is coined 'library pimping' and is achieved via implicit conversions.
 * In this exercise, we will try out some implicit conversions from integers to Joda's DateTime,
 * so we can write little DSL like statements like 1 day + 2 hours.
 * It is also possible to define implicit parameters
 */

object Exercise08 {


   def stringToList(s: String) : List[Char] = {
     //build in: our String will be converted to Scala's RichString, because this is defined a Scala
     //object called Predef. This is imported by the compiler by default.
     //
     s.toList
   }

  
}
object TimeUtils {
   case class DurationBuilder(timeSpan: Long) {

    def now = new DateTime().getMillis()

//    def seconds = TODO your implementation here...

//    def minutes = TODO your implementation here...

//    def hours = TODO your implementation here...

//    def days = TODO your implementation here...
   }

  //TODO define some implicits that convert integers and longs to durations and builders to make it all work

   def seconds(in:Long) = in * 1000L

   def minutes(in:Long) = seconds(in) * 60L

   def hours(in:Long) = minutes(in) * 60L
  
   def days(in:Long) = hours(in) * 24L
}

case class RichDuration(val duration: Duration) {

    def millis = duration.getMillis()
  
    def afterNow = new DateTime().plus(duration)

    def +(that: RichDuration) = RichDuration(this.duration.plus(that.duration))
}