package org.scalalabs.basic.lab01

abstract class Currency(val symbol: String)

class Euro(val euro: Int, val cents: Int = 0) extends Currency("EUR") with Ordered[Euro]{
  def inCents: Int = euro * 100 + cents

  def +(other: Euro) = Euro.fromCents(inCents + other.inCents)

  def *(n: Int) = Euro.fromCents(n * inCents)

  override def toString = s"$symbol: $euro,${if (cents > 0) f"$cents%02d" else "--"}"
  
  override def compare(that:Euro) = inCents - that.inCents

}

object Euro {
  def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)

}