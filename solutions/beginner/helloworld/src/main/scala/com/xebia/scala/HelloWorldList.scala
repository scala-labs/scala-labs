package com.xebia.scala

object HelloWorldList {
	def firstElementInListOfStrings(listOfStrings:List[String]):String = {
		listOfStrings.first
	}
 
	def concatLists(list1:List[String], list2:List[String]):List[String] = {
		list1 ::: list2
	}
 
	def elementExists(theList:List[String], elementToSearchFor:String):Boolean = {
		theList.exists(_ == elementToSearchFor)
    }

 	def oddElements(listOfInts:List[Int]):List[Int] = {
 		listOfInts.filter ( _ % 2 != 0)
    }
}
