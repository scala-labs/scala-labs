package org.scalalabs.advanced.lab01

/**
 * Scala's actors are units of execution that process messages.
 * Messages can be send to an Actor using the ! method. They are then stored in the Actor's mailbox.
 * If an Actor does not have any messages in it's mailbox to process, it is suspended.
 * Messages are processed asynchronously and  Actor's process only one message at a time.
 * Because the actor's state can only be modified by sending a message, and messages are processed serially
 * they are thread-safe by default.
 * It is therefore an interesting alternative to the normal concurrency model of taking locks. 
 */

import scala.actors.Actor
import scala.collection.mutable.HashMap
import org.joda.time.DateTime

class EchoActor extends Actor {
  /**
   * implement the act method so that it replies any message back to the sender
   */
  def act = exit //TODO
}

sealed trait CountEvent
case object Inc
case object Dec
case object Curr

class Counter extends Actor {
  private var value: Int = 0

  /**implement the act method so that it:
   * <ul>
   * <li> increments a private counter when it receives an 'Inc' message.</li>
   * <li> decrements the counter when it receives a 'Dec' message.</li>
   * <li> replies the current value of the counter back tot the sender.
   * </ul>
   */
  def act = exit //TODO
}

/**
 * The various events that are used in our chatserver examples.
 * The events have the following meaning:
 * <ol>
 * <li>When receiving a ChatLog message the client, or server, should reply to the sender with all messages currently in its chat.
 *     This should be wrapped in a Messages class.</li>
 * <li>When receiving a Message from an Actor, the Chatserver should store it in its private chatLog, and also send this message
 *     to the client given by the from key.
 *  </li>
 * <li>When the client receives an AnonymousMessage, it should add it to its chatLog.</li>
 * <li>When the server receives a BroadcastMessage, it should send it to all logged in clients.</li>
 * <li>When the server receives an Add message, it should add the chatclient to its list of logged in in clients.</li>
 * <li>When the server receives an Remove message, it should remove the chatclient from its list of logged in in clients.</li>
 * <li>When the client or server receives an Messages message, it should reply its chatLog
 */
sealed trait ChatEvent
case object ChatLog extends ChatEvent
case class Messages(msg: List[String]) extends ChatEvent
case class Message(from: String, msg: String) extends ChatEvent
case class BroadcastMessage(from: String, msg: String) extends ChatEvent
case class AnonymousMessage(msg: String) extends ChatEvent
case class Add(who: ChatClient) extends ChatEvent
case class Remove(who: String) extends ChatEvent



class SimpleChatClient extends Actor {
  private val loggedInAt = new DateTime
  private var messages: List[String] = Nil

  /**
   * Implement the act method so that it stores any message in a private list when it receives the AnonymousMessage class.
   * The client should send back all messages it currently has when it receives a ChatLog message.
   */
  def act = exit //TODO
}

/**
 * The self annotation indicates that this ChatServer should be mixed in with the ChatMgt and the MessageMgt traits.
 */
trait ChatServer extends Actor {
  self: ChatMgt with MessageMgt =>

  protected def messageMgt: PartialFunction[Any, Unit]

  protected def chatMgt: PartialFunction[Any, Unit]

  /**
   * The chatserver is repsonsible for two things: message management and chat management.
   *
   */
  def act = exit //TODO
}


trait ChatClientOps extends Actor {
  self: ChatClient =>

  private val loggedInAt = new DateTime
  private var chatLog: List[String] = Nil

  /**
   * Implement this method so that the given message is posted to the server. Should be wrapped in a Message class.
   */
  def post(message: String) = {
    println("Client " + name + " posts message " + message + " to server")
    //TODO
  }

  /**
   * Implement this method so that the given message is broadcast to the server. Should be wrapped in a BroadcastMessage class.
   */
  def broadCast(message: String) = {
    println("Client " + name + " posts broadcast message " + message + " to server")
    server ! BroadcastMessage(name, name + ": " + message)
  }

  def login: Unit = {
    this.start
    //TODO add this client to the chatServer
  }

  /**
   * In the act method the client should add any AnonymousMessage it receives to its private chatLog
   * When it recieves a ChatLog message, it should reply its chatLog to the sender, wraped inside a Messages class. 
   */
  def act = exit //TODO

}

case class ChatClient(val name: String, val server: Actor) extends ChatClientOps

/**
 * * Implements an im-memory message store.
 * <p/>
 * The self-type annotation (self: Actor =>) means that this trait can only be used when mixed in with an Actor.
 */
trait MessageMgt {
  self: Actor with ChatMgt =>
  protected var messages: List[String] = Nil

  /**
   * Implement messageMgt to handle the following messages
   * <ul>
   * <li>In case of a Message(from, msg) message, it should send it the client given by the from key.
   *     In order to do this, the ChatMgt trait is mixed in so that the client can be obtained from there.</li>
   * <li>In case of an AnonymousMessage(msg) message, it should add it to its private list of messages.</li>
   * <li>In case of a BroadcastMessage(msg) message, it should send it to all logged in clients.</li>
   * <li>In case of an ChatLog message, it should reply the list of messages to the sender, wraped inside a Messages object</li>
   * </ul>
   */
  protected def messageMgt: PartialFunction[Any, Unit] = {
    //TODO implement
    case _ =>

  }
}

/**
 * Implements listener management.
 * <p/>
 * The self-type annotation (self: Actor =>) means that this trait can only be used when mixed in with an Actor.
 */
trait ChatMgt {
  self: Actor =>

  protected var sessions = new HashMap[String, Actor]

  /**
   * Implement chatMgt so that it does the following:
   * <ul>
   *  <li>In case of a Add(user) message, add the user to the list of logged in clients.</li>
   *  <li>In case of a Remove(user) message, remove the user from the list of logged in clients.</li>
   * </ul>
   */
  protected def chatMgt: PartialFunction[Any, Unit] = {
    //TODO implement
    case _ =>

  }

  protected def shutdown: Unit = {
    sessions.clear
  }
}

/**
 * Our main ChatService, with the required traits mixed in.
 */
class ChatService extends ChatServer with MessageMgt with ChatMgt

