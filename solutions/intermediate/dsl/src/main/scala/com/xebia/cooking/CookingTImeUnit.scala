package com.xebia.cooking
 
object CookingTimeUnit extends Enumeration {
	type CookingTimeUnit = Value
  val minutes = Value("minutes")
  val hours = Value("hours")

  def parse(value:String):CookingTimeUnit.Value=filter(value==_.toString).next
}

