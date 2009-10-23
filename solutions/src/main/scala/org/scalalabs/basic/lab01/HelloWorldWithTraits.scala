package com.xebia.scala

object HelloWorldWithTraits extends HelloTrait with WorldTrait {
	def hello:String = helloMethod + " " + worldMethod
} 
