package com.xebia.cooking

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

class CookBookTest extends JUnit3Suite {
    def testParseOfcookBookTextShouldReturn2Recipes {
		val cookbook:CookBook = CookBookDSL.parseCookBook(cookbookText)
		expect (2) { cookbook.getListOfRecipes.length}
    }
	def testASearchForSummerRecipesReturnsEggplantRecipe() {
		val cookbook:CookBook = CookBookDSL.parseCookBook(cookbookText)
		val summerRecipes = cookbook.findRecipesBySeason(Seasons.summer) 
		expect (1) { summerRecipes.length}
		expect ("EggplantWithMinceMeat") { summerRecipes.first.name }
	}
	def testASearchForTacosRecipesReturnsTacosRecipe() {
		val cookbook:CookBook = CookBookDSL.parseCookBook(cookbookText)
		expect (1) { cookbook.findRecipesByName("TacoDish").length}
	}
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

name TacoDish
season allYear
category easy
cookingtime 45 minutes
preheat oven at 180 degrees
fry 500 gram mincemeat
add 1 can kidneyBeans
add 500 gram tomatoes
add 1 piece peperoni
serve with tacoShels, salad, tomatoes
add 1 bottle mexicanSauce
"""
}