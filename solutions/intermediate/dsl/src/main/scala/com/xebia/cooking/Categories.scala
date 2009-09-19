package com.xebia.cooking

object Categories extends Enumeration {
	type Categories = Value
  val Easy = Value("easy")
  val Average = Value("average")
  val Difficult = Value("difficult")
  
  def parse(value:String):Categories.Value=filter(value==_.toString).next
}

