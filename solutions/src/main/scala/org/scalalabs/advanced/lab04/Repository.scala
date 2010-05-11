package org.scalalabs.advanced.lab04

import org.scala_libs.jpa.{ThreadLocalEM, ScalaEntityManager, LocalEMF}


object Repository extends LocalEMF("scalajpalab") with ThreadLocalEM