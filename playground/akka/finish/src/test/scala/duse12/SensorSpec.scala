package duse12

import akka.actor.Actor._
import org.scalatest.{BeforeAndAfterAll, WordSpec}
import org.scalatest.matchers.ShouldMatchers
import akka.testkit.TestKit
import akka.util.duration._
import duse12.messages.{VehiclePassed, VehicleQueued, VehicleDetected}
import scala.Some

/**
 *  Specs for the Sensor Actor.
 *  The Sensor should:
 *  1. turn a received VehicleDetected message into a VehicleQueued message and async forward it to the junction if crossedMarker is false
 *  2. turn a received VehicleDetected message into a VehiclePassed message and async forward it to the junction if crossedMarker is true
 *  3. keep track of the amount queued on the lane (increment in VehicleDetected(id, false) and decrement on VehicleDetected(id, true))
 *  4. handle !! for both VehicleDetected msgs and return the amount queued as an Int
 *  Use the TestKit to confirm expected forwarded messages
 */
class SensorSpec extends WordSpec with BeforeAndAfterAll with ShouldMatchers with TestKit {

  val sensor = actorOf(new Sensor(LANE.WEST, junction = testActor)).start

  override protected def afterAll(): scala.Unit = {
    sensor.stop
    stopTestActor
  }

  "The Sensor" should {
    "turn a received VehicleDetected message into a VehicleQueued message and forward it to the junction if crossedMarker is false " in {
      within(500 millis) {
        val detected = VehicleDetected(1)
        sensor ! detected
        expectMsg(VehicleQueued(1, LANE.WEST,1,detected.timestamp))
        expectMsg(1)
      }
    }
    "turn a received VehicleDetected message into a VehiclePassed message and forward it to the junction if crossedMarker is true " in {
      within(500 millis) {
        val detected = VehicleDetected(1,true)
        sensor ! detected
        expectMsg(VehiclePassed(1,LANE.WEST,0,detected.timestamp))
        expectMsg(0)
      }
    }
  }
}