package duse12

import akka.actor.{ActorRef, Actor}

/**
 * The junction of roads, receives vehicles and communicates with TrafficLights,
 * Handles commands, asynchronously passes messages to listener (which should be the JunctionQueryModel ActorRef).
 */
class Junction(lights: Map[LANE.HEADING, ActorRef], listener: ActorRef) extends Actor {

  def receive = {
    //TODO implement
    case _ =>

  }

}