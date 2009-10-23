package com.xebia.cooking

import scala.util.parsing.combinator.syntactical._
import Seasons._
import Categories._
import CookingTimeUnit._

object CookBookDSL extends StandardTokenParsers {
  lexical.delimiters ++= List("(", ")", ",", " ", "\n")
  lexical.reserved ++= List("name", "season", "fry", "pack", "can", "bag", "piece", "at", "for", "bottle"
  					, "gram", "oven", "preheat", "bake", "serve", "with","add","degrees", "category", "cookingtime")

  def parseCookBook(cookBook:String):CookBook = {
		cookbook(new lexical.Scanner(cookBook)) match {
		  case Success(recipeList, _) => new CookBook(recipeList)
 		  case Failure(msg, _) => throw new ParseErrorException(msg)
		  case Error(msg, _) => throw new ParseErrorException(msg)
		}
  }
  
  def cookbook: Parser [List[Recipe]] =
    rep(recipe) ^^ { recipeList: List[Recipe] => recipeList }
  
  def recipe: Parser [Recipe] =
		recipeName ~ seasons ~ category ~ cookingTime ~ listOfSteps  ^^ {
     case name ~ seasons ~ category ~ cookingTime ~ listOfSteps  
        => new Recipe(name, seasons, new Steps(listOfSteps), category, cookingTime) 
  }
	
  def listOfSteps: Parser [List[Step]] =
	rep(step)  ^^ { stepList: List[Step] => stepList}
		
  def recipeName: Parser[String] =
    "name" ~ ident ^^ { case "name" ~ name => name }

  def seasons: Parser[List[Seasons]] =
    "season" ~ listOfSeasons ^^ { case "season" ~ listOfSeasons => listOfSeasons }

  def listOfSeasons: Parser[List[Seasons]] =
    repsep(season ,",") ^^ { listOfSeasons: List[Seasons] => listOfSeasons }

  def season: Parser[Seasons] = 
    ident ^^ { case season => Seasons.parse(season) }
  	
  def step: Parser[Step] = 
	(fryStep | preHeatStep | serveWithStep | addStep | bakeStep) ^^ { case step => step }
	
  def fryStep: Parser[Step] = 
  	"fry" ~ amount ~ ident ^^ { case "fry" ~ amount ~ ingredient => (new FryStep (new Ingredient(ingredient,amount)))}
 
  def preHeatStep: Parser[Step] = 
	"preheat" ~ "oven" ~ "at" ~ numericLit ~ "degrees" ^^ { case "preheat" ~ "oven" ~ "at" ~ degrees ~ "degrees" => (new OvenPreHeatStep(degrees.toInt))}
  	
  def bakeStep: Parser[Step] = 
	"bake" ~ "for" ~ numericLit ~ ident ^^ 
    { case "bake" ~ "for" ~ time ~ unit 
		 => new BakeStep(new CookingTime(time.toInt, CookingTimeUnit.parse(unit)))
	}
  	
  def addStep: Parser[Step] = 
  	"add" ~ amount ~ ident ^^ { case "add" ~ amount ~ ingredient => (new AddStep (new Ingredient(ingredient,amount)))}

  def serveWithStep: Parser[Step] = 
  	"serve" ~ "with" ~ listOfIngredients ^^ 
  		{ case "serve" ~ "with" ~ ingredientList  => (new ServeWithStep(ingredientList))}

  def listOfIngredients: Parser[List[Ingredient]] = 
	repsep(ingredient, ",") ^^ { ingredientList: List[Ingredient] => ingredientList }
		
  def ingredient: Parser[Ingredient] = 
	opt(amount) ~ ident ^^
  	{ case Some(amount) ~ ingredient => (new Ingredient(ingredient,amount))
  	  case  _ ~ ingredient => {new Ingredient(ingredient)}
  	}
      
  def amount: Parser[Amount] = 
  	numericLit ~ ("can"|  "pack"| "bag"| "piece"| "gram"| "bottle")  ^^ { case amount ~ amountUnit => (new Amount(amount.toInt, amountUnit.toString)) } 	
  
  def category: Parser[Categories] = 
    "category" ~ ident ^^ { case "category" ~ category => Categories.parse(category) }

  def cookingTime: Parser[CookingTime] = 
    "cookingtime" ~ numericLit ~ ident ^^ { case "cookingtime" ~ cookingTime ~ cookingTimeUnit
      => new CookingTime(cookingTime.toInt, CookingTimeUnit.parse(cookingTimeUnit)) }
} 

class ParseErrorException extends java.lang.RuntimeException {
	def this(msg:String) = { this()
        // TODO: constructor doesn't work yet
        // TODO: remove this debug statement
		println("msg: " + msg)
	}
}