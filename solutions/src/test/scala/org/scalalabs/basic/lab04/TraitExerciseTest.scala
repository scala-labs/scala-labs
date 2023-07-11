package org.scalalabs.basic.lab04
import org.scalalabs.basic.lab04.Level._
import org.specs2.mutable.Specification
import org.specs2.specification._

class TraitExerciseTest extends Specification {
  sequential

  private val enableAllLevels = Map(Debug -> true, Info -> true)
  private val disableAllLevels = Map(Debug -> false, Info -> false)
  private val firstDebugStatement =
    "Debug   org.scalalabs.basic.lab04.DummyService Prepare sending"
  private val infoStatement = (msg: String) =>
    s"Info    org.scalalabs.basic.lab04.DummyService $msg successfully sent"
  private val lastDebugStatement =
    "Debug   org.scalalabs.basic.lab04.DummyService Done"

  "Exercise 1: Logger Trait" should {
    "log all events" in new cleanLogger {
      SimpleLogger.logConfig = enableAllLevels

      val msg = "message"
      val service = new DummyService()

      service.sendSomething(msg)

      SimpleLogger.logHistory.size === 3

      val first :: second :: third :: Nil = SimpleLogger.logHistory

      first === firstDebugStatement
      second === infoStatement(msg)
      third === lastDebugStatement
    }

    "not create log message in case level is not enabled" in new cleanLogger {
      SimpleLogger.logConfig = disableAllLevels

      var longStringCreated = ""

      private def createLongString = {
        longStringCreated = "Scala " * 1000
        longStringCreated
      }

      val impl = new AnyRef with Loggable
      impl.debug(createLongString)

      SimpleLogger.logHistory must beEmpty
      longStringCreated ==== ""
    }
  }

  trait cleanLogger extends Scope {
    SimpleLogger.clearHistory()
  }
}
