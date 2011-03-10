package com.xebia.snippet

import java.io.Console


import org.specs._
import org.specs.runner._
import org.specs.Sugar._
import scala.xml._
import org.junit.runner.RunWith


class TrackerTest  extends SpecificationWithJUnit {
   "my test" should {
      "correctly process" in {
        true must be_==(true)
      }
   }
}