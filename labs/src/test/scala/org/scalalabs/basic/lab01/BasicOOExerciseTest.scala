package org.scalalabs.basic.lab01

import java.lang.{ IllegalArgumentException => IAE }
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
@RunWith(classOf[JUnitRunner])
class BasicOOExerciseTest extends Specification {

  "Exercise 1: Creating an Euro" should {
    "be initialized correctly" in {
      skipped("Uncomment and fix me")
      //      val e = new Euro(1, 5)
      //      e.euro ==== 1
      //      e.cents ==== 5

    }
    "have correct default value for cents" in {
      skipped("Uncomment and fix me")
      //      val e = new Euro(2)
      //      e.euro ==== 2
      //      e.cents ==== 0
    }
    "convert amount correctly to cents" in {
      skipped("Uncomment and fix me")
      //      val e = new Euro(2, 45)
      //      e.inCents ==== 245
    }
    "be created by cents" in {
      skipped("Uncomment and fix me")
      //      val e = Euro.fromCents(245)
      //      e.euro ==== 2
      //      e.cents ==== 45
    }
  }
  "Exercise 2: Calculating with Euros" should {
    "add another euro correctly" in {
      skipped("Uncomment and fix me")
      //      val res = new Euro(1, 50) + new Euro(2, 70)
      //      res.euro ==== 4
      //      res.cents ==== 20
    }
    "multiply correctly by a factor" in {
      skipped("Uncomment and fix me")
      //      val res = new Euro(1, 50) * 3
      //      res.euro ==== 4
      //      res.cents ==== 50
    }
  }
  "Exercise 3: toString of Euro" should {
    "have correct  representation" in {
      skipped("Uncomment and fix me")
      //      val e = new Euro(2, 5)
      //      e.toString ==== "EUR: 2,05"
      //      val e2 = new Euro(2)
      //      e2.toString ==== "EUR: 2,--"
    }
  }
  "Exercise 4: Euro with Order trait" should {
    "be correctly orderable" in {
      skipped("Uncomment and fix me")
      //      val e1 = new Euro(1, 5)
      //      val e2 = new Euro(4, 53)
      //      val e3 = new Euro(8, 10)
      //      e3 > e1 must beTrue
      //      val list = List(e2, e1, e3)
      //      list.sorted ==== List(e1, e2, e3)
    }
  }
}
