package org.scalalabs.basic.lab01

import org.specs2.mutable.Specification

class Specs2ExerciseTest extends Specification {

  "Euro" should {
    "be divisible" in {
      val input = new Euro(1, 20)
      val result = input / 5
      result.euro ==== 0
      result.cents ==== 24
    }

    "produce an IllegalArgumentException if divided with <= 0" in {
      new Euro(1, 2) / 0 must throwAn[IllegalArgumentException]
    }

  }

}
