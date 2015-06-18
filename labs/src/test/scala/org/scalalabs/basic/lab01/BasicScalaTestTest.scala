package org.scalalabs.basic.lab01

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit._

@RunWith(classOf[JUnitRunner])
class BasicScalaTestTest extends WordSpecLike with AssertionsForJUnit with Matchers {
  var state = 0
  "ScalaTest" should {
    "have a great variety of matchers" in {
      state = 2
      state should equal(2)

      state should be < 10
      "Scala" should startWith("S")
      an[NumberFormatException] should be thrownBy {
        "NaN".toInt
      }
      "12:30" should fullyMatch regex """\d{2}:\d{2}"""

      Seq(1,2,3) should contain theSameElementsAs(Seq(2,3,1))
      Seq(1,2,3) should be(sorted)
    }

    "correctly handle state" in {
      state should equal(2)
    }
  }

}
