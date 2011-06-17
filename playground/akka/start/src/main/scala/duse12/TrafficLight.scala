package duse12

import akka.actor.Actor
import akka.event.EventHandler
import duse12.messages._
import scala.Some

/**
 * A TrafficLight, should receive GreenLight messages, turns the lightSwitch to red or green.
 */
class TrafficLight(lane: LANE.HEADING, lightSwitch: LightSwitch) extends Actor {

  def receive = {
    //TODO implement
    case _ =>
  }
}