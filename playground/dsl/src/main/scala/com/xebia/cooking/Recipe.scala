package com.xebia.cooking
 
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Buffer
import Seasons._
import Categories._
import CookingTimeUnit._

case class Recipe (name:String, seasons:List[Seasons], steps:Steps, category:Categories, cookingTime:CookingTime) {
	def this (name:String, seasons:List[Seasons]) = this(name,seasons, new Steps,Categories.Average, new CookingTime(1,CookingTimeUnit.hours))
	def this () = this (null,null,new Steps,Categories.Average, new CookingTime(1,CookingTimeUnit.hours))
	def this (name:String, seasons:List[Seasons], steps:List[Step], category:Categories) = { this (name, seasons, new Steps(steps), category, new CookingTime(1,CookingTimeUnit.hours))}

  	override def toString =
  		"Recipe: (name="+name+",seasons="+seasons+",category: " + category + ",cooking time: " + cookingTime + ")\n" + steps + printIngredients
 
 	def getIngredients:List[Ingredient] = {
 		val ingredients:ListBuffer[Ingredient] = new ListBuffer[Ingredient]
 		steps.list.foreach(step => step.findIngredients.foreach(ingredient => ingredients.+(ingredient)))
 		ingredients.toList
 	}
 	
 	def printIngredients:String = "Ingredients: " + getIngredients.mkString(",")
  	def addStep(step:Step) = steps.add(step)
}

class Ingredient (val name:String, val amount:Amount) {
	override def toString = {
		if (amount==null) name 
		else name+":"+amount
	} 
	def this(name:String) = this(name,null)
}

class Amount (val amount:Int, val unitName:String) {
	override def toString = amount+":"+unitName
}

abstract class Step {
	protected val operation:String = "Step"
	def findIngredients:List[Ingredient] = List()
}

abstract class StepWithAnIngredient(protected val ingredient:Ingredient) extends Step {
	var ingredients:List[Ingredient]=List[Ingredient](ingredient)
	override def findIngredients:List[Ingredient] = ingredients.toList
	def addIngredient(ingredient:Ingredient):StepWithAnIngredient = { 
	  ingredients = List(ingredient) ::: ingredients
	  this
	}
	override def toString = operation+":"+ingredients.mkString(",")
}

class FryStep (override val ingredient:Ingredient) extends StepWithAnIngredient(ingredient:Ingredient) {
	override val operation="fry"
}

class BakeStep(val cookingTime:CookingTime) extends Step {
	override val operation="bake"
	override def toString = operation + ":" + cookingTime
}

class AddStep(override val ingredient:Ingredient) extends StepWithAnIngredient(ingredient:Ingredient) {
	override val operation="add"
}

class OvenPreHeatStep(val degrees:Int) extends Step {
	override val operation="preheat"
	override def toString = operation+":"+degrees
}

class ServeWithStep(val ingredients:List[Ingredient]) extends Step {
	override val operation="serveWith"
	val ingredientList:Buffer[Ingredient] = new ListBuffer().++(ingredients)
	override def findIngredients:List[Ingredient] = ingredientList.toList
	override def toString = operation+":"+ingredientList
}

class Steps {
	val list:Buffer[Step] = new ListBuffer
	def add(step:Step) = list+=step
	def this (listOfSteps:List[Step]) {
		this()
		list.appendAll(listOfSteps)
	}
	def addListOfSteps(listOfSteps:List[Step]) {
		list.appendAll(listOfSteps)
	}
	override def toString = list.mkString("\n")
}

class CookingTime(val amount:Int, val unit:CookingTimeUnit) {
	override def toString = amount + " " + unit
}