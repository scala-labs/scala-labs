package org.scalalabs.basic.lab05

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.WordSpecLike

class FuturesSpec extends WordSpecLike with Matchers with BeforeAndAfterAll {

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

  "FuturesExcersise" should {
    "1: calculate average conversion rate returned by all services" in {
      val testServices = Seq(serviceBankA, serviceBankB, serviceBankC)
      val (elapsed, result) = measure {
        //TODO: implement
        -1
      }
      elapsed should be(3000 +- 500)
      result should be((120 + 123 + 125) / 3)
    }
    "2. return first received conversion rate as String" in {
      val testServices = Seq(serviceBankA, serviceBankB, serviceBankC)
      val (elapsed, result) = measure {
        //TODO: implement
        ""
      }
      elapsed should be(1000 +- 500)
      result should be(serviceBankC.returnRate.toString)
    }
    "3. return first received conversion rate within 2 seconds" in {
      val serviceBankD = new CurrencyService(120)(4000)
      val testServices = Seq(serviceBankA, serviceBankD)
      val (elapsed, result) = measureEither {
        ???
      }
      elapsed should be(2000 +- 500)
      result.left.map(_.getMessage) should be(Left("timeout"))
    }
    "4. return all conversion rates sequentially using futures" in {
      val testServices = Seq(serviceBankA, serviceBankB, serviceBankC)
      val (elapsed, result) = measure {
        //TODO: implement
        ""
      }
      elapsed should be(6000 +- 500)
      result should be(Seq(120, 123, 125))
    }
  }

}

