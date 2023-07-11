package org.scalalabs.basic.lab04

/**
 * In this exercise you learn to isolate common behavior in traits.
 *
 * Beneath you see an implementation of a SimpleLogger.
 * This logger is used in the DummyService class in an intrusive manner,
 * directly referencing the logger implementation.
 *
 * To complete this exercise you have to provide a Loggable trait, that
 * contains all logging methods (debug and info). Replace the intrusive
 * implementation of SimpleLogger in the DummyService with this Loggable trait
 * so that the DummyService directly can use the the logging methods without
 * the need to create its own logger.
 */
object Level extends Enumeration {
  type Level = Value
  val Debug, Info = Value
}
import Level._

class SimpleLogger(clazz: String) {
  import SimpleLogger._

  /** Logs debug */
  def debug(msg: => Any): Unit = log(Debug, msg)

  /** Log info */
  def info(msg: => Any): Unit = log(Info, msg)

  def isLevelEnabled(level: Level): Boolean = logConfig.getOrElse(level, false)

  private def log(level: Level, msg: => Any): Unit = {
    println(s"Level enabled: ${isLevelEnabled(level)}")
    if (isLevelEnabled(level)) {
      val logMsg = f"$level%-7s $clazz $msg"
      logHistory = logHistory :+ logMsg
      println(logMsg)
    }
  }
}

object SimpleLogger {
  var logHistory: Seq[String] = Seq.empty
  def clearHistory(): Unit = logHistory = Seq.empty[String]
  var logConfig = Map(Debug -> false, Info -> true)

  def apply(clazz: String) = new SimpleLogger(clazz)
}

class DummyService extends Loggable {

  def sendSomething(msg: Any): Unit = {
    debug("Prepare sending")
    info(s"$msg successfully sent")
    debug("Done")
  }
}

trait Loggable { self =>
  private val logger = SimpleLogger(self.getClass.getName)
  def debug(msg: => Any): Unit =
    logger.debug(msg)
  def info(msg: => Any): Unit =
    logger.info(msg)
}
