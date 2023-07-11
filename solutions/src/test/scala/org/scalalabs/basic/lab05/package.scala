package org.scalalabs.basic

import java.util.{Timer, TimerTask}

import scala.concurrent.duration.FiniteDuration

package object lab05 {
  def measureEither[T](exec: => T): (Int, Either[Throwable, T]) = {
    import scala.util.control._
    val start = System.currentTimeMillis()
    val res = Exception.allCatch.either(exec)
    val elapsed = System.currentTimeMillis() - start
    (elapsed.toInt, res)
  }

  def measure[T](exec: => T): (Int, T) = {
    val (elapsed, res) = measureEither(exec)
    elapsed -> res.getOrElse(
      throw new IllegalArgumentException("unexpected error")
    )
  }

  def scheduleOnce(delay: FiniteDuration)(f: => Unit) = {
    val task = new TimerTask {
      override def run() = f
    }
    val timer = new Timer(true);
    timer.schedule(task, delay.toMillis)
    timer
  }

}
