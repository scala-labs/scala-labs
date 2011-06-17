package duse12

import org.scalatest.{BeforeAndAfterAll, WordSpec}
import org.scalatest.matchers.ShouldMatchers
import akka.testkit.TestKit
import akka.actor.Actor._
import akka.util.duration._
import scala.Some
import duse12.messages.{DecisionsResponse, DecisionsRequest, JunctionDecision}
/**
 * Spec for JunctionQueryModel.
 * The JunctionQueryModel should:
 *  return a history list of decisions made.
 *  call the JunctionQueryModel with !! DecisionsRequest() and check the result.
 */
class JunctionQueryModelSpec extends WordSpec with BeforeAndAfterAll with ShouldMatchers with TestKit {

  val commands = actorOf(new JunctionQueryModel(listener = Some(testActor))).start

  override protected def afterAll(): scala.Unit = {
    commands.stop
    stopTestActor
  }

  "The JunctionQueries" should {
    "return a history list of decisions made" in {
      within(500 millis) {
        commands ! JunctionDecision(LANE.WEST)
        commands ! JunctionDecision(LANE.EAST)
        commands ! JunctionDecision(LANE.NORTH)
        expectMsg(JunctionDecision(LANE.WEST))
        expectMsg(JunctionDecision(LANE.EAST))
        expectMsg(JunctionDecision(LANE.NORTH))
        commands !! DecisionsRequest() match {
          case Some(response:DecisionsResponse) => {
            response.history.size should be (3)
            response.history.contains(JunctionDecision(LANE.WEST)) should be (true)
            response.history.contains(JunctionDecision(LANE.EAST)) should be (true)
            response.history.contains(JunctionDecision(LANE.NORTH)) should be (true)
          }
          case None => fail("no response")
        }
      }
    }
  }
}