package com.xebia.util

import org.specs._
import com.xebia.utils.Utils
import net.liftweb.common.Failure

  class UtilsTest  extends SpecificationWithJUnit {
   "Utils" should {
      "correctly convert date" in {
        val parsed = Utils.parseDate("")

        val res = parsed match {
          case f:Failure if(f.msg == "Unparseable date: \"\"") => true
          case _ => false
        }
        res must be_==(true)
      }
   }
}