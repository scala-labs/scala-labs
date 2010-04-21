package org.scalalabs.advanced.lab01

/**
 * An introduction to scala actors.
 */

import scala.actors.Actor
import scala.actors.Actor._

class EchoActor extends Actor {
  def act = loop {
    react {
      case s => reply("Got message: " + s)
    }
  }
}

case object ChatLog
case class Messages(msg: List[String])
case class Remove(who: Actor)
case class Add(who: Actor)

class ChatServerActor extends Actor /*with Logging*/ {
  private var messages: List[String] = Nil
  private var listeners: List[Actor] = Nil

  def act = loop {
    react {
      case message: String => {
        println("Got message: " + message)
        messages = message :: messages
      }
      case ChatLog => reply(Messages(messages))
      case Add(who) => listeners = who :: listeners
      case Remove(who) => listeners -= who
      case _ => 
    }
  }
}