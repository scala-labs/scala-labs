package duse12

import org.apache.camel.component.language.LanguageEndpoint


/**
 * Enumeration for identification of Lanes
 */
object LANE extends Enumeration {
  type HEADING = Value
  val NORTH = Value(2,"NORTH")
  val EAST = Value(3,"EAST")
  val WEST = Value(1,"WEST")
  def isVertical(v:LANE.HEADING) = (v == EAST || v == WEST)
  def isHorizontal(v:LANE.HEADING) = (v == NORTH)
  def nextClockwise(v:LANE.HEADING) = LANE((v.id % 3) +1)
}
