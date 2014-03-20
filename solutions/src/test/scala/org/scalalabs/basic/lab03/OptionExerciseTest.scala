package org.scalalabs.basic.lab03

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import OptionExercise01._
/**
 * @see OptionExercise
 */
@RunWith(classOf[JUnitRunner])
class OptionExerciseTest extends Specification {
  val rooms = Map(1 -> Some("12"), 2 -> None, 3 -> Some("locked"), 4 -> Some("14"), 5 -> Some("8"), 6 -> Some("locked"))

  "OptionExercise01" should {
    "correctly show the state of filled room (e.g. Some(12))" in {
      roomState(rooms, 1) === "12"
    }
    "correctly show the state of an empty room (None)" in {
      roomState(rooms, 2) === "empty"
    }
    "correctly show the state of a room that is not available (Some(locked))" in {
      roomState(rooms, 3) === "not available"
    }
    "correctly show the state of a room that does not exist (no entry in Map)" in {
      roomState(rooms, 100) === "not existing"
    }
  }
  "OptionExercise02" should {
    "calculate total amount of people in rooms" in {
      OptionExercise02.totalPeopleInRooms(rooms) === 34
    }
  }

}