package duse12

import akka.actor.{ActorRef, Actor}
import collection.mutable.HashMap
import akka.event.EventHandler
import duse12.messages._

/**
 * The junction of roads, receives vehicles and communicates with TrafficLights,
 * Handles commands, asynchronously passes messages to listener (which should be the JunctionQueryModel ActorRef).
 */
class Junction(lights: Map[LANE.HEADING, ActorRef], listener: ActorRef) extends Actor {
  val map = new HashMap[LANE.HEADING, Int]()

  def receive = {
    case msg: VehicleQueued => {
      // process queued vehicles,
      EventHandler.info(this, "Vehicle queued on %s lane.".format(msg.lane))
      map.get(msg.lane) match {
        case Some(count) => map.put(msg.lane, msg.queueCount)
        case None => map.put(msg.lane, msg.queueCount)
      }
      listener.forward(msg)
    }
    case msg: VehiclePassed => {
      EventHandler.info(this, "Vehicle passed on %s lane.".format(msg.lane))
      // process passed vehicles,
      map.get(msg.lane) match {
        case Some(count) => map.put(msg.lane, msg.queueCount)
        case None => map.put(msg.lane, msg.queueCount)
      }
      listener.forward(msg)
    }
    case msg: ControlTraffic => {
      // decide on green light,
      EventHandler.info(this, "Deciding which lane gets the green signal.")
      if (map.isEmpty || map.values.max == 0) {
        lights.values.foreach(_ !! GreenLight(on = false))
      } else {
        EventHandler.info(this, "North:" + map.getOrElse(LANE.NORTH, 0))
        EventHandler.info(this, "West:" + map.getOrElse(LANE.WEST, 0))
        EventHandler.info(this, "East:" + map.getOrElse(LANE.EAST, 0))
        // let the most queued vehicles through
        val greenLane = map.maxBy(_._2)._1
        listener ! JunctionDecision(greenLane)
        for (value <- LANE.values) {
          if (value == greenLane) {
            lights.get(value).foreach {
              _ !! GreenLight(on = true)
            }
          } else {
            lights.get(value).foreach {
              _ !! GreenLight(on = false)
            }
          }
        }
        EventHandler.info(this, "Green signal for %s lane." format greenLane)
      }
    }
    case msg: ResetJunction => {
      //reset the junction.
      EventHandler.info(this, "Resetting junction.")
      map.clear
      listener.forward(msg)
    }
    case msg@_ => {
      EventHandler.error(this, "Unknown msg '%s' received in Junction." format msg)
    }
  }

}