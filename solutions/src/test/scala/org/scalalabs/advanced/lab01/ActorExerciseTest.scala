package org.scalalabs.advanced.lab01

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import org.scalalabs.util.{LoggingTest, ScalaLabsConfig, Logging}
import org.junit.{Before, Test}

/**
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 2:12:16 PM
 */

class ActorExerciseTest extends JUnitSuite with LoggingTest {

  @Before  
  override def initialize() {
    import ScalaLabsConfig._
    config.getString("scalalabs", "0")
//    println("found version " + version)
  }

 @Test
 def shouldReply = {
   val echo = new EchoActor
   echo.start
   assertEquals("Got message: Hello EchoActor", (echo !? "Hello EchoActor"))

 }

  @Test
  def shouldAddMessage = {
    val chatServer = new ChatServerActor
    chatServer.start
    chatServer ! "message1"
    chatServer ! "message2"

    val msg: Option[List[String]] = chatServer !? ChatLog match {
      case Messages(msg) => Some(msg)
      case _ => None
    }
    assertEquals(List("message2", "message1"), msg.getOrElse(Nil))
  }
}