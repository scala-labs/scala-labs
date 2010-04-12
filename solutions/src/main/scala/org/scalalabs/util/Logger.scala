package org.scalalabs.util

import net.lag.logging.Logger
import net.lag.configgy.{ParseException, Configgy}

/**
 * User: arjan
 * Date: Apr 12, 2010
 * Time: 6:23:19 PM
 */

trait Logging {
  @transient lazy val log = Logger.get(this.getClass.getName)
}

object ScalaLabsConfig extends Logging {
  val config = {
    try {
      println("Configuring Configgy")
      Configgy.configureFromResource("scalalabs.conf", getClass.getClassLoader)
      log.info("Logging configured from classPath")
    } catch {
      case pe: ParseException => throw new IllegalStateException(
        "\nCan't find 'scalalabs.conf' configuration file." +
                "\nAborting.")
    }
    Configgy.config
  }

}