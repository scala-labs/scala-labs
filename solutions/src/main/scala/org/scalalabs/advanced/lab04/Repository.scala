package org.scalalabs.advanced.lab04

import org.scala_libs.jpa.{ ThreadLocalEM, ScalaEntityManager, LocalEMF }

/**
 * ThreadLocal ScalaEntityManager for local usage
 */
object Repository extends LocalEMF("scalajpalab") with ThreadLocalEM with ScalaEntityManagerFactory {
  def sem() = {
    if (!isOpen) newEM else this
  }
}

trait ScalaEntityManagerFactory {
  def sem(): ScalaEntityManager
}

