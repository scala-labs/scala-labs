package org.scalalabs.basic.lab03

/**
 * Functions and control structures
 */

object Exercise06 {
  def plusOne(x: Int): Int = {
    //implement this using a partial function
    plus(1, x)
  }

  def plus(x: Int, y: Int): Int = {
    x + y
  }

  def using[A <: {def close(): Unit}, B](closable: A)(f: A => B): B =
    try {
      f(closable)
    } finally {
      closable.close()
    }
}