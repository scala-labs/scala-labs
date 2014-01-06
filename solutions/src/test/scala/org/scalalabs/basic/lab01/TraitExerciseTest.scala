package org.scalalabs.basic.lab01

import java.lang.{ IllegalArgumentException => IAE }
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification._
import org.specs2.specification.BeforeExample
import org.specs2.specification.AfterExample
import Level._
@RunWith(classOf[JUnitRunner])
class TraitExerciseTest extends Specification {
  sequential
  val enableAllLevels = Map(Debug -> true, Info -> true)
  val disableAllLevels = Map(Debug -> false, Info -> false)
  val firstDebugStatement = "Debug   org.scalalabs.basic.lab01.DummyService Prepare sending"
  val infoStatement = (msg: String) => s"Info    org.scalalabs.basic.lab01.DummyService $msg successfully sent"
  val lastDebugStatement = "Debug   org.scalalabs.basic.lab01.DummyService Done"

  "Exercise 1: Logger Trait" should {
    "log all events" in new cleanLogger {
      SimpleLogger.logConfig = enableAllLevels
      val msg = "message"
      val service = new DummyService().sendSomething(msg)

      SimpleLogger.logHistory.size === 3
      val first :: second :: third :: Nil = SimpleLogger.logHistory
      first === firstDebugStatement
      second === infoStatement(msg)
      third === lastDebugStatement
    }
    "not create log message in case level is not enabled" in new cleanLogger {
      SimpleLogger.logConfig = disableAllLevels
      var longStringCreated = ""
      def createLongString = {
        longStringCreated = "Scala " * 1000000
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
