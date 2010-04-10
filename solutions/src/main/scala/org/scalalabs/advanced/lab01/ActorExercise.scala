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

