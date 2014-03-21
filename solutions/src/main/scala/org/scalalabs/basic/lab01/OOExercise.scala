package org.scalalabs.basic.lab01

import scala.language.implicitConversions
import org.scalalabs.basic.lab01.DefaultCurrencyConverter
abstract class Currency(val symbol: String)

class Euro(val euro: Int, val cents: Int = 0) extends Currency("EUR") with Ordered[Euro] {
  lazy val inCents: Int = euro * 100 + cents

  def +(other: Euro) = Euro.fromCents(inCents + other.inCents)

  def *(n: Int) = Euro.fromCents(n * inCents)

  override lazy val toString = s"$symbol: $euro,${if (cents > 0) f"$cents%02d" else "--"}"

  override def compare(that: Euro) = inCents - that.inCents

}

object Euro {
  def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)

  implicit class EuroInt(val i: Int) extends AnyVal {
    def *(euro: Euro) = euro * i
  }

  //implicit def fromDollar(dollar: Dollar): Euro = Euro.fromCents((DefaultCurrencyConverter.toEuroCents(dollar.inCents)).toInt)

  implicit def fromDollar(dollar: Dollar)(implicit converter:CurrencyConverter): Euro = Euro.fromCents(converter.toEuroCents(dollar.inCents))
  

}

class Dollar(val dollar: Int, val cents: Int = 0) extends Currency("USD") {
  def inCents: Int = dollar * 100 + cents
}

