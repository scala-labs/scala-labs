package org.scalalabs.basic.lab03

/**
 * Functions and basic pattern matching
 */

object Exercise06 {
  def plusOne(x: Int): Int = {
    //implement this using a partial function
    error("fix me")
  }

  def plus(x: Int, y: Int): Int = {
    x + y
  }

  def using[A <: {def close(): Unit}, B](closable: A)(f: A => B): B = {
    error("fix me")
  }
}