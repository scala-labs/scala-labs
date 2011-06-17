package duse12

import duse12.messages._
import akka.actor.{ActorRef, Actor}
import akka.event.EventHandler

/**
 * Handle forwarded commands and queries.
 */
class JunctionQueryModel(listener:Option[ActorRef]=None) extends Actor {
  private var events = List[JunctionEvent]()

  def receive = {
    case msg: JunctionEvent => {
      events = msg::events
      listener.foreach(_ ! msg)
    }
    case msg: DecisionsRequest => {
      EventHandler.info(this, "Getting request")
      val history = events.filter(p=> p.isInstanceOf[JunctionDecision])
      EventHandler.info(this, "Reply with response")
      self.reply(DecisionsResponse(history))
    }
    case _ => {

    }
  }
}