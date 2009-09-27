package com.xebia.cooking

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

class RecipeTest extends JUnit3Suite {
    def testFryStepShouldHaveSomeIngredients {
		val fryStep:FryStep=new FryStep(new Ingredient("Salmon"))
		expect (1) { fryStep.findIngredients.size}
    	fryStep.addIngredient(new Ingredient("Rice"))
    	expect (2) { fryStep.findIngredients.size}
    	expect ("fry:Rice,Salmon") { fryStep.toString }
    }
    def testEggplantRecipePrintsOK {
		val cookbook:CookBook = CookBookDSL.parseCookBook(cookbookText)
		val actualResult=cookbook.findRecipesByName("EggplantWithMinceMeat").first.toString
		expect (expectedOutput) { cookbook.findRecipesByName("EggplantWithMinceMeat").first.toString}
    }
    def testIngredientHasPublicNameAttribute {
    	val rice:Ingredient=new Ingredient("Rice")
    	expect("Rice") { rice.name}
    }
    val expectedOutput="""Recipe: (name=EggplantWithMinceMeat,seasons=List(summer),category: easy,cooking time: 45 minutes)
preheat:180
fry:mincemeat:500:gram
fry:eggplant:1:piece
add:mashedTomatoes:1:can
add:groundCheese:1:bag
bake:30 minutesIngredients: mincemeat:500:gram,eggplant:1:piece,mashedTomatoes:1:can,groundCheese:1:bag"""
	val cookbookText:String = """name EggplantWithMinceMeat
season summer
category easy
cookingtime 45 minutes
preheat oven at 180 degrees
fry 500 gram mincemeat
fry 1 piece eggplant
add 1 can mashedTomatoes
add 1 bag groundCheese
bake for 30 minutes
"""
	val illegalText:String = """name illegal
This won't parse and should throw an exeption"""
}
