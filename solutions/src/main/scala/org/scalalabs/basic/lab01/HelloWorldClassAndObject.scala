package org.scalalabs.basic.lab01

object HelloWorldClassAndObject {
	def apply(initialText:String):HelloWorldClassAndObject = {
		new HelloWorldClassAndObject {
			val text=initialText
		}
	}
}

abstract class HelloWorldClassAndObject {
	val text:String
	def echo:String = text
}