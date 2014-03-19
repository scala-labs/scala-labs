package org.scalalabs.basic.lab01
/**
 * Classes used in [[org.scalalabs.basic.lab01.OOExerercise]]
 */
trait CurrencyConverter {
  def toEuroCents(dollarCents: Int): Int
}

trait DefaultCurrencyConverter extends CurrencyConverter {
  val conversionRate = 1.3598
  def toEuroCents(dollarCents: Int): Int = 
		(dollarCents.toDouble * conversionRate).toInt
}

object DefaultCurrencyConverter extends DefaultCurrencyConverter
 