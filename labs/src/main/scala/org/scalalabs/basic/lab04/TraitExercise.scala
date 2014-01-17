package org.scalalabs.basic.lab04
import Level._
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

class SimpleLogger(clazz: String) {
  import SimpleLogger._
  /**
   * Logs debug
   */
  def debug(msg: => Any) = log(Debug, msg)
  /**
   * Log info
   */
  def info(msg: => Any) = log(Info, msg)

  private def log(level: Level, msg: => Any) = {
    def isLevelEnabled(level: Level) = logConfig.getOrElse(level, false)
    if (isLevelEnabled(level)) {
      val logMsg = f"$level%-7s $clazz $msg"
      logHistory = logHistory :+ logMsg
      println(logMsg)
    }
  }
}

object SimpleLogger {

  var logHistory = Seq.empty[String]
  def clearHistory() = logHistory = Seq.empty[String]
  var logConfig = Map(Debug -> false, Info -> true)

  def apply(clazz: String) = new SimpleLogger(clazz)
}

class DummyService  {
  
  /**the logger must be removed. 
   * Move it to a Loggable trait that can be mix-in in any class that needs logging.
   * Finally, mix-in the Loggable trait in this class in order to log the statments
   * in the sendSomething method*/
  val logger = SimpleLogger(getClass().getName())
  
  def sendSomething(msg: Any) = {
    logger.debug("Prepare sending")
    logger.info(s"$msg successfully sent")
    logger.debug("Done")
  }
}





