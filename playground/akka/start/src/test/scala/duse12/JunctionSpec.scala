package duse12

import org.scalatest.{BeforeAndAfterAll, WordSpec}
import org.scalatest.matchers.ShouldMatchers
import akka.testkit.TestKit
import java.util.Date
import akka.util.duration._
import akka.actor.Actor._
import duse12.messages._

/**
 * Specs for the Junction.  The Junction should :
 * 1. decide on a JunctionDecision and async send the JunctionDecision to the listener on a ! ControlTraffic() message
 * 2. handle and async forward VehicleQueued and VehiclePassed messages to the listener, keep track of queued per lane
 * 3. decide on the lane with the most queued vehicles, only one green light at a time
 * 4. communicate with the lights using !! (blocking)
 * 5. reset the junction on a ResetJunction() msg (which sets all lights to red) and async forward the ResetJunction() message
 */
class JunctionSpec extends WordSpec with BeforeAndAfterAll with ShouldMatchers with TestKit {

  val statusWest = new MockLight()
  val statusNorth = new MockLight()
  val statusEast = new MockLight()

  val lightWest = actorOf(new TrafficLight(LANE.WEST, statusWest)).start
  val lightNorth = actorOf(new TrafficLight(LANE.NORTH, statusNorth)).start
  val lightEast = actorOf(new TrafficLight(LANE.EAST, statusEast)).start
  val queries = actorOf(new JunctionQueryModel()).start
  val lights = Map(LANE.NORTH -> lightNorth, LANE.EAST -> lightEast, LANE.WEST -> lightWest)
  val junction = actorOf(new Junction(lights, listener = testActor)).start

  override protected def afterAll(): scala.Unit = {
    junction.stop
    lights.values.foreach(_.stop)
    queries.stop
    stopTestActor
  }

  def newDate = new Date(System.currentTimeMillis())

  "The Junction" should {
    "forward VehicleQueued messages to the listener " in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
    "control traffic when a ControlTraffic() command msg is received and forward a JunctionDecision" in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
    "forward VehiclePassed messages to the listener" in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
    "reset queue count for every lane on ResetJunction() msg and forward ResetJunction() message to the listener" in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
    "decide on lane which has most queueCount vehicles compared all other lanes" in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
    "pick the next maximum queueCount lane vehicles have passed on the decided lane" in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
    "not decide if there are no vehicles queued (implied by not sending a message to the listener)" in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
  }
}