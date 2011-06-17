package duse12

import akka.actor.{ ActorRef, Actor }
import akka.event.EventHandler
import duse12.messages._
/**
 * Sensor Actor, handles VehicleDetected messages for a lane.
 * VehicleDetected where crossedMarker is false means the Vehicle is approaching,
 * VehicleDetected where crossedMarker is true means the Vehicle has passed the junction.
 */
class Sensor(lane: LANE.HEADING, junction: ActorRef) extends Actor {

  def receive = {
    //TODO implement
    case _ =>

  }
}
