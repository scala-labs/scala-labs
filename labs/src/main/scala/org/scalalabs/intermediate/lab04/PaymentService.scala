package org.scalalabs.intermediate.lab04

import java.util.Date

object PaymentService {
  var history: List[Order] = Nil
  var verboseLogMode: Boolean = false

  def pay(orders: List[Order]): Response = {
    history ++= orders
    new Response(true, "Accepted")
  }

  def getSortedHistory(sort: (Order, Order) => Boolean): List[Order] = {
    history.sortWith(sort)
  }

  def getHistory(): List[Order] = {
    history
  }

  def reset() {
    history = Nil
  }

}

case class Response(
  approved: Boolean,
  message: String)

case class Order(
  val userId: String,
  val paymentMethod: PaymentMethod,
  val amount: Int) {
  require(paymentMethod != null)
  require(amount > 0)
}

trait PaymentMethod

case class PaymentCard(override val expirationDate: Date, override val holderName: String) extends PaymentMethod with Expireable with Belongs

case class Cache() extends PaymentMethod

trait Expireable {
  val expirationDate: Date
}

trait Belongs {
  val holderName: String
  def firstName = holderName.split(" ").head
}

