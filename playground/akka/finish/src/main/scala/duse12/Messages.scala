package duse12 {

import java.util.Date
import akka.util.Duration

package messages {

import java.util.concurrent.atomic.AtomicInteger

object MsgUtils {
  private val counter = new AtomicInteger()

  def newDate = new Date(System.currentTimeMillis())

  def genNextId = counter.incrementAndGet()

}

import MsgUtils._

/**
 * Event trait
 */
trait JunctionEvent {
}

/**
 * message sent to the sensor that a vehicle is detected,
 * crossedMarker indicates if the vehicle crossed the marker on the road (passed the junction)
 * or if it is approaching the junction
 */
case class VehicleDetected(id: Int, crossedMarker: Boolean = false, timestamp: Date = newDate) extends JunctionEvent

/**
 * message sent from sensor to junction, that a vehicle is queued on the lane
 */
case class VehicleQueued(id: Int, lane: LANE.HEADING, queueCount: Int, timestamp: Date = newDate) extends JunctionEvent

/**
 * message sent from the sensor to the junction that a vehicle has passed the junction
 */
case class VehiclePassed(id: Int, lane: LANE.HEADING, queueCount: Int, timestamp: Date = newDate) extends JunctionEvent

/**
 * Command to switch a traffic light to green or red. (true is green)
 */
case class GreenLight(on:Boolean)

/**
 * Command to control traffic
 */
case class ControlTraffic()

/**
 * Command to reset the junction
 */
case class ResetJunction() extends JunctionEvent

/**
 * The decision the junction has made at a specific time, result of ControlTraffic command.
 */
case class JunctionDecision(lane: LANE.HEADING) extends JunctionEvent

/**
 * Query for decisions made in the past
 */
case class DecisionsRequest()

/**
 * Response to the Query for decisions made in the past
 */
case class DecisionsResponse(history: List[JunctionEvent])

}
}