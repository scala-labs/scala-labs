package org.scalalabs.advanced.lab02

import scala.util.parsing.combinator.JavaTokenParsers

class ParserCombinatorExercise extends JavaTokenParsers {

  /**
   * Exercise 1:
   * Create a context-free grammar that correctly parses a sentence
   */
  def particle: Parser[Any] = "the"
  def noun: Parser[Any] = "fox" | "dog"
  def adjective: Parser[Any] = "quick" | "brown" | "lazy"
  def verb: Parser[Any] = "jumps"
  def preposition: Parser[Any] = "over"

  def nounPhrase: Parser[Any] = particle ~ rep(adjective) ~ noun
  def prepositionPhrase: Parser[Any] = preposition ~ nounPhrase
  def verbPhrase: Parser[Any] = verb ~ opt(prepositionPhrase | nounPhrase)
  def sentence: Parser[Any] = nounPhrase ~ verbPhrase

  /**
   * Exercise 2:
   * Create grammar that correctly parses a simple arithmetic problem and
   * calculates the result
   */

  def parsedDigit: Parser[Double] = floatingPointNumber ^^ (_.toDouble)

  def plus: Parser[Double] = parsedDigit ~ "+" ~ math ^^
    { case arg1 ~ "+" ~ arg2 => arg1 + arg2 }

  def minus: Parser[Double] = parsedDigit ~ "-" ~ math ^^
    { case arg1 ~ "-" ~ arg2 => arg1 - arg2 }

  def math: Parser[Double] = plus | minus | parsedDigit

}
