package org.scalalabs.advanced.lab01

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._

/**
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 2:12:16 PM
 */

class ActorExerciseTest {

 @Test
 def shouldReply = {
   val echo = new EchoActor
   echo.start
   assertEquals("Got message: Hello EchoActor", (echo !? "Hello EchoActor"))

 }

}