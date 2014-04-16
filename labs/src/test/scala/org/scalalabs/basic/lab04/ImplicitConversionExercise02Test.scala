package org.scalalabs.basic.lab04
import org.joda.time.Duration
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.joda.time._
import Exercise02._
import scala.util.parsing.json.JSONObject
/**
 * @see ImplictConversionExercise02
 */
@RunWith(classOf[JUnitRunner])
class ImplicitConversionExercise02Test extends Specification with DeactivatedTimeConversions {

  "Exercise01" should {
    "have a working money DSL" in {
      skipped("Uncomment and fix me")
//            Euro(2, 0) must be_==~(2 euros)
//            Euro(0, 25) must be_==~(25 cents)
//            Euro(2, 25) must be_==~(2 euros 25 cents)
    }
  }  
  
  "Exercise02" should {
    import JsonConverter._
    val euro = Euro(1, 2)
    val json = JSONObject(Map("symbol" -> "EUR", "amount" -> s"${euro.euros},${euro.cents}"))
    "convert Euro to json" in {
      val out = convertToJson(euro)
      out ==== json
    }
    "convert json to Euro" in {
      val in = parseFromJson[Euro](json)
      euro === in
    } 
  }
}

 
