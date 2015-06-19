package org.scalalabs.advanced.lab01

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import org.junit.{ Before, Test }

/**
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 2:12:16 PM
 */

class ActorExerciseTest extends JUnitSuite {

  //  @Before
  //  override def initialize() {
  //    import ScalaLabsConfig._
  //    config.getString("scalalabs", "0")
  //  }
  //
  @Test
  def shouldEcho = {
    val echo = new EchoActor
    echo.start
    assertEquals("Got message: Hello EchoActor", (echo !? "Hello EchoActor"))

  }

  @Test
  def shouldIncrementAndDecrement = {
    val ctr = new Counter
    ctr.start

    assertEquals(0, (ctr !? Curr))
    ctr ! Inc
    assertEquals(1, (ctr !? Curr))
    ctr ! Inc
    assertEquals(2, (ctr !? Curr))
    ctr ! Dec
    assertEquals(1, (ctr !? Curr))
  }

  @Test
  def clientShouldAddMessageToPrivateLog = {
    val chatClient = new SimpleChatClient
    chatClient.start

    chatClient ! Message("testuser", "message1")
    chatClient ! Message("testuser", "message2")

    val msg: Option[List[String]] = chatClient !? ChatLog match {
      case Messages(msg) => Some(msg)
      case _ => None
    }
    assertEquals(List("message2", "message1"), msg.getOrElse(Nil))
  }

  @Test
  def shouldAddMessageToChatLog = {
    val chatServer = new ChatService
    chatServer.start
    chatServer ! AnonymousMessage("message1")
    chatServer ! AnonymousMessage("message2")

    val msg: Option[List[String]] = chatServer !? ChatLog match {
      case Messages(msg) => Some(msg)
      case _ => None
    }
    assertEquals(List("message2", "message1"), msg.getOrElse(Nil))
  }

  @Test
  def shouldAddListenersAndPublishMessagesToAll = {
    val chatServer = new ChatService
    chatServer.start

    val client1 = new ChatClient("client1", chatServer)
    val client2 = new ChatClient("client2", chatServer)

    client1.login
    client2.login
    client1.post("first message")
    client2.post("second message")
    Thread.sleep(500)

    val msg1: Option[List[String]] = client1 !? ChatLog match {
      case Messages(msg) => Some(msg)
      case _ => None
    }
    assertEquals(List("client1: first message"), msg1.getOrElse(Nil))

    val msg2: Option[List[String]] = client2 !? ChatLog match {
      case Messages(msg) => Some(msg)
      case _ => None
    }

    assertEquals(List("client2: second message"), msg2.getOrElse(Nil))

    val msg: Option[List[String]] = chatServer !? ChatLog match {
      case Messages(msg) => Some(msg)
      case _ => None
    }
    assertEquals(List("client2: second message", "client1: first message"), msg.getOrElse(Nil))

    //This message should now be received by all logged in clients
    client1.broadCast("a broadcast message")

    Thread.sleep(500)

    val msg3: Option[List[String]] = client2 !? ChatLog match {
      case Messages(msg) => Some(msg)
      case _ => None
    }

    assertEquals(List("client1: a broadcast message", "client2: second message"), msg3.getOrElse(Nil))

  }
}