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
        //TODO implement
        fail("not implemented yet")
      }
    }
  }
}