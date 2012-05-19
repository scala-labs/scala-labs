package org.scalalabs.intermediate.lab04

import java.util.Date
import org.junit.Assert._
import org.junit.{ After, Test }

/**
 *  Lab 04 Interoperability Between Java and Scala
 *  This lab works only with language areas that are not the same for Java and Scala
 *
 *  We assume that you have a service that was written in Scala and now you need
 *  to write Java wrapper to access its methods from java module.
 *  You can change PaymentServiceClient.java only to fix this test!
 *
 *  Useful links:
 *  {@link http://www.codecommit.com/blog/java/interop-between-java-and-scala} and
 *  {@link http://stackoverflow.com/questions/4524868/can-i-use-scala-list-directly-in-java}
 */

class PaymentClientTest {

  @Test
  def dummyBecauseItDoesNotCompile = {
    assertEquals(true, true);
  }
  /*
  val paymentClient = new PaymentServiceClient()

  @After def resetServiceState(){
    paymentClient.resetState
  }

  def testIfOrderAccepted(madePayment: =>Unit){
     madePayment
     assertEquals(paymentClient.findAllOrders.size, 1)
  }

  @Test
  def testLogVerboseMode = {
     val verbosityFlag = paymentClient.isVerboseLogMode
     paymentClient.setVerboseLogMode(!verbosityFlag)
     assertEquals(!verbosityFlag, paymentClient.isVerboseLogMode);
  }

  @Test
  def testCachePayment = {
    testIfOrderAccepted{
      paymentClient.cachePayment("John Doe", 124)
    }
  }

  @Test
  def testCardPayment = {
    testIfOrderAccepted{
       paymentClient.cardPayment("John Smith", 12, new Date())
    }
  }

  @Test
  def testVoucherPayment = {
    testIfOrderAccepted{
        paymentClient.voucherPayment("John Stiles", 14)
    }

    paymentClient.findAllOrders.head.paymentMethod match {
      case h: Belongs => assertEquals("John", h.firstName)
      case _ => fail
    }
  }

  @Test
  def testFindAllOrders = {
    paymentClient.cardPayment("John Doe", 186, new Date())
    paymentClient.cardPayment("Richard Miles", 180, new Date())
    val orders = paymentClient.findAllOrders()
    assertEquals(orders(0).amount, 186)
    assertEquals(orders(1).amount, 180)
  }
*/
}
