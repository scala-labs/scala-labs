package duse12

import akka.actor.Actor
import akka.event.EventHandler
import duse12.messages._
import scala.Some

/**
 * A TrafficLight, should receive GreenLight messages, turns the lightSwitch to red or green.
 */
class TrafficLight(lane: LANE.HEADING, lightSwitch: LightSwitch) extends Actor {
  // false is red
  var green: Option[Boolean] = None

  def receive = {
    case msg: GreenLight => {
      def switch(command: Boolean, currentState: Boolean): Boolean = {
        if (command != currentState) {
          EventHandler.info(this, "TrafficLight on '%s' lane is switched to %s" format (lane, if (command) "green" else "red"))
          if (command) {
            lightSwitch.switchToGreen
          } else {
            lightSwitch.switchToRed
          }
        }
        command
      }
      green = green.map(switch(msg.on, _)).orElse{ Some(switch(msg.on, !msg.on)) }
      self.reply_?(green.get)
    }
    case msg@_ => {
      EventHandler.error(this, "Unknown msg '%s' received in TrafficLight." format msg)
    }
  }
}