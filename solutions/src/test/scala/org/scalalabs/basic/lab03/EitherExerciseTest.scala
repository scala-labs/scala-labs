package org.scalalabs.basic.lab03

import org.junit.runner.RunWith
import org.scalalabs.basic.lab03.EitherExercise01._
import org.specs2.matcher.EitherMatchers
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

/**
 * @see OptionExercise
 */
@RunWith(classOf[JUnitRunner])
class EitherExerciseTest extends Specification with EitherMatchers {

  "EitherExercise01" should {
    "correctly calculate reciprocal of integer" in {
      reciprocal(Right(5)) must beRight(0.2)
      reciprocal(Right(-2)) must beRight(-0.5)
    }

    "correctly calculate reciprocal of integer encoded as string" in {
      reciprocal(Left("10")) must beRight(0.1)
      reciprocal(Left("-4")) must beRight(-0.25)
    }

    "correctly encapsulate error on inputting 0 value" in {
      reciprocal(Right(0)).left.map(_.getMessage) must beLeft("Reciprocal of 0 does not exist!")
    }

    "correctly calculate reciprocal of unparseable string" in {
      reciprocal(Left("foo")).left.map(_.getMessage) must beLeft("For input string: \"foo\"")
      reciprocal(Left("bar")).left.map(_.getMessage) must beLeft("For input string: \"bar\"")
    }

  }
}