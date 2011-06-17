package duse12

import org.scalatest.{BeforeAndAfterAll, WordSpec}
import org.scalatest.matchers.ShouldMatchers
import akka.actor.Actor._
import akka.util.duration._
import duse12.messages.GreenLight
import scala.Some
import akka.testkit.TestKit
import java.util.concurrent.{TimeUnit, TimeoutException, CyclicBarrier}

/**
 * Specs for TrafficLight Actor.
 * The TrafficLight should:
 * 1. switch to green if the light is not green already on a GreenLight(true) message
 * 2. switch to red if the light is not red already on a GreenLight(false) message
 * 3. handle both ! and !!
 * Use a CyclicBarrier and the MockLight in this test for the async ! test.
 */
class TrafficLightSpec extends WordSpec with BeforeAndAfterAll with ShouldMatchers with TestKit {
  val barrier = new CyclicBarrier(2);
  val mockLight = new MockLight(Some(barrier))

  val light = actorOf(new TrafficLight(LANE.WEST, mockLight)).start

  override protected def afterAll(): scala.Unit = {
    light.stop
    stopTestActor
  }

  "The TrafficLight" should {
    "switch the light to green if the light is not already green on ! GreenLight(true)" in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
    "switch the light to red if the light is not already red on ! GreenLight(false)" in {
      within(500 millis) {
        //TODO implement
        fail("not implemented yet")

      }
    }
  }
}