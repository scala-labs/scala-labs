package com.xebia.cooking
 
object Seasons extends Enumeration {
	type Seasons = Value
  val summer = Value("summer")
  val winter = Value("winter")
  val autumn = Value("autumn")
  val spring = Value("spring")
  val allYear = Value("allYear")

  def parse(value:String):Seasons.Value=filter(value==_.toString).next
}
