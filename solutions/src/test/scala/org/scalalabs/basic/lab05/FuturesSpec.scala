package org.scalalabs.basic.lab05

import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.language.postfixOps

class FuturesSpec extends AnyWordSpecLike with Matchers with BeforeAndAfterAll {
  class CurrencyService(val returnRate: Int)(latency: Int) {
    def rateUSD: Future[Int] = {
      Future[Int] {
        Thread.sleep(latency)
        returnRate
      }
    }
  }
  val serviceBankA = new CurrencyService(120)(3000)
  val serviceBankB = new CurrencyService(123)(2000)
  val serviceBankC = new CurrencyService(125)(1000)
  val servicesBankABC = Seq(serviceBankA, serviceBankB, serviceBankC)

  "FuturesExcersise" should {
    "1: calculate average conversion rate returned by all services" in {
      val testServices = servicesBankABC
      val (elapsed, result) = measure {
        val futures = testServices map { service => service.rateUSD }
        val res = Future.sequence(futures).map(seq => seq.sum / seq.length)
        Await.result(res, 5 seconds)
      }
      elapsed should be(3000 +- 500)
      result should be((120 + 123 + 125) / 3)
    }
    "2. return first received conversion rate as String" in {
      val testServices = servicesBankABC
      val (elapsed, result) = measure {
        val futures = testServices map { service => service.rateUSD }
        val future = Future.firstCompletedOf(futures).map(_.toString)
        Await.result(future, 6 seconds)
      }
      elapsed should be(1000 +- 500)
      result should be(serviceBankC.returnRate.toString)
    }
    "3. return first received conversion rate within 2 seconds" in {
      val bankD = new CurrencyService(120)(4000)
      val testServices = Seq(serviceBankA, bankD)
      val (elapsed, result) = measureEither {
        val futures = testServices map { service => service.rateUSD }
        val promise = Promise[Int]
        Future.firstCompletedOf(futures).foreach(value =>
          promise.trySuccess(value))
        Future
        scheduleOnce(2 seconds) {
          promise.tryFailure(new Exception("timeout"))
        }
        Await.result(promise.future, 5 seconds)
      }
      elapsed should be(2000 +- 500)
      result.left.map(_.getMessage) should be(Left("timeout"))
    }
    "4. return all conversion rates sequentially using futures" in {
      val testServices = servicesBankABC
      def recurse(services: Seq[CurrencyService]): Future[Seq[Int]] = services match {
        case head :: tail => head.rateUSD.flatMap(res => recurse(tail).map(seq => res +: seq))
        case Nil => Future(Seq())
      }
      def withRecurions = recurse(testServices)

      def withFold = testServices.foldLeft(Future(Seq.empty[Int]))((cum, next) => cum.flatMap(v => next.rateUSD.map(v :+ _)))

      val (elapsed, result) = measure {
        //Await.result(withRecurions, 8 seconds)
        Await.result(withFold, 8 seconds)
      }
      elapsed should be(6000 +- 500)
      result should be(Seq(120, 123, 125))
    }
  }

}

