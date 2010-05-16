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
  def act = loop {
    react {
      case s => reply("Got message: " + s)
    }
  }
}

sealed trait CountEvent
case object Inc
case object Dec
case object Curr

class Counter extends Actor {
  private var value: Int = 0

  def act = loop {
    react {
      case Inc => value += 1
      case Dec => value -= 1
      case Curr => reply(value)
    }
  }
}

sealed trait ChatEvent
case object ChatLog extends ChatEvent
case class Message(from: String, msg: String) extends ChatEvent
case class BroadcastMessage(from: String, msg: String) extends ChatEvent
case class AnonymousMessage(msg: String) extends ChatEvent
case class Messages(msg: List[String]) extends ChatEvent
case class Remove(who: String) extends ChatEvent
case class Add(who: ChatClient) extends ChatEvent



class SimpleChatClient extends Actor {
  private val loggedInAt = new DateTime
  private var messages: List[String] = Nil

  protected def messageMgt: PartialFunction[Any, Unit] = {
    case Message(from, msg) => messages = msg :: messages
    case ChatLog => reply(Messages(messages))
  }

  def act = loop {
    react(messageMgt)
  }
}

trait ChatServer extends Actor  {
  self: ChatMgt with MessageMgt =>

  protected def messageMgt: PartialFunction[Any, Unit]

  protected def chatMgt: PartialFunction[Any, Unit]

  def act = loop {
    react {
      chatMgt orElse messageMgt
    }
  }

  def handleMsg: PartialFunction[Any, Unit] = {
     case m @ Message(from, msg) => {
       messages = msg :: messages
//       chats.valuesIterator.foreach(c => c ! m)
     }
  }
}


trait ChatClientOps extends Actor {
  self: ChatClient =>

  private val loggedInAt = new DateTime
  private var chatLog: List[String] = Nil

  def post(message: String) = {
    println("Client " + name + " posts message " + message + " to server")
    server ! Message(name, name + ": " + message)
  }

  def broadCast(message: String) = {
      println("Client " + name + " posts broadcast message " + message + " to server")
      server ! BroadcastMessage(name, name + ": " + message)
  }


  def login = {
     this.start
     server ! Add(self)
  }

  def act = loop {
    react {
      case m @ AnonymousMessage(msg) => {
        println("Client " + self.name + " got message " + msg);
        chatLog = msg :: chatLog
      }
      case ChatLog => reply(Messages(chatLog))
    }
  }
}

case class ChatClient(val name: String, val server: Actor) extends ChatClientOps

/**
 * * Implements an im-memory message store.
 * <p/>
 * The self-type annotation (self: Actor =>) means that this trait can only be used when mixed in with an Actor.
 */
trait MessageMgt {
  self: Actor with ChatMgt =>
  protected var messages : List[String] = Nil

  protected def messageMgt: PartialFunction[Any, Unit] = {
    case m @ Message(from, msg) => {
      println("Got message from " + from + " message: " + msg)
      sessions(from) ! AnonymousMessage(msg)
      messages = msg :: messages
    }
    case m @ BroadcastMessage(from, msg) => {
      println("Got broadcast message from " + from + " message: " + msg)
      sessions.valuesIterator.foreach(_ ! AnonymousMessage(msg))
      messages = msg :: messages
    }
    case m @ AnonymousMessage(msg) => {
      messages = msg :: messages
    }

    case ChatLog => reply(Messages(messages))
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

  protected def chatMgt: PartialFunction[Any, Unit] = {
    case Add(user) => {
      println(String.format("User %s has been added", user.name))
      sessions += (user.name -> user)
    }

    case Remove(user) => {
      //      log.info("User [%s] has logged out", username)
      println(String.format("User %s has logged out", user))
      val chat = sessions(user)
      sessions -= user
    }
  }

  protected def shutdown: Unit = {
    sessions.clear
  }
}

class ChatService extends ChatServer with MessageMgt with ChatMgt

