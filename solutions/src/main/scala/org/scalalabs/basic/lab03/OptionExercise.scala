package org.scalalabs.basic.lab03
import scala.util.control._

package object lab03 {

  /**
   * This map contains sample testdata to clarify this exercise.
   * It contains key value pairs where:
   * - the key is a room number
   * - the value can be:
   * -- the amount of people in the room (filled: Some("10"), empty: None)
   * -- the room is not available (Some("locked"))
   */
  val sampleRooms = Map(1 -> Some("12"), 2 -> None, 3 -> Some("locked"), 4 -> Some("14"), 5 -> Some("8"), 6 -> Some("locked"))
}

object OptionExercise01 {

  /**
   * Implement the room state method that should return the state of a room as a String as follows:
   * - filled: Some("12") -> 12
   * - empty:  None -> "empty"
   * - locked: Some("locked") -> "not available"
   * - does not exist: "not existing"
   */
  def roomState(rooms: Map[Int, Option[String]], room: Int): String = {
    rooms.get(room).map { roomState ⇒
      roomState.map { value ⇒
        if (value == "locked") "not available"
        else value
      }
        .getOrElse("empty")
    }
      .getOrElse("not existing")
    //better
    rooms.getOrElse(room, Some("not existing")).map( roomState ⇒
      if (roomState == "locked") "not available" else roomState
    ).getOrElse("empty")

  }


}

object OptionExercise02 {

  /**
   * Calculate the total amount of people in all rooms
   * Hint: make use of a for expression and scala.util.control.Exception.allCatch opt (...)
   * to convert a possible numeric String (e.g. Some("12")) to an integer
   */
  def totalPeopleInRooms(rooms: Map[Int, Option[String]]): Int = {
    //with get
    rooms.values.map(room => Exception.allCatch.opt(room.get.toInt))

    //better
    rooms.values.flatten.map(room => Exception.allCatch.opt(room.toInt).getOrElse(0)).sum


    val res = for {
      occupationOpt ← rooms.values
      occupation ← occupationOpt
      occupationNo ← Exception.allCatch.opt(occupation.toInt)
    } yield occupationNo
    res.sum
  }

}