package org.scalalabs.basic.lab04
import org.joda.time.Duration

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.joda.time._
/**
 * @see ImplictConversionExercise02
 */
@RunWith(classOf[JUnitRunner])
class ImplictConversionExercise02Test extends Specification with DeactivatedTimeConversions {

  "Exercise01" should {
    "have a working money DSL" in {
      skipped("Uncomment and fix me")
      //      Euro(2, 0) must be_==~(2 euros)
      //      Euro(0, 25) must be_==~(25 cents)
      //      Euro(2, 25) must be_==~(2 euros 25 cents)
    }
  }
}

