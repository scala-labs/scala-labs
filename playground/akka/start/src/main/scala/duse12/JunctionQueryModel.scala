package duse12

import duse12.messages._
import akka.actor.{ActorRef, Actor}
import akka.event.EventHandler

/**
 * Handle forwarded commands and queries.
 */
class JunctionQueryModel(listener:Option[ActorRef]=None) extends Actor {

  def receive = {
    //TODO implement
    case _ =>

  }
}