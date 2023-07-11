package org.scalalabs.advanced.lab02

import scala.util.parsing.combinator.JavaTokenParsers
import sys._

/** Created by IntelliJ IDEA. User: lieke Date: May 2, 2010
  */

class ParserCombinatorExercise extends JavaTokenParsers {

  /** Exercise 1: Create a context-free grammar that correctly parses a sentence
    */
  def particle: Parser[Any] = "the"
  def noun: Parser[Any] = "fox" | "dog"
  def adjective: Parser[Any] = "quick" | "brown" | "lazy"
  def verb: Parser[Any] = "jumps"
  def preposition: Parser[Any] = "over"

  def nounPhrase: Parser[Any] = error("TODO")
  def prepositionPhrase: Parser[Any] = error("TODO")
  def verbPhrase: Parser[Any] = error("TODO")
  def sentence: Parser[Any] = error("TODO")

  /** Exercise 2: Create grammar that correctly parses a simple arithmetic
    * problem and calculates the result
    */

  def parsedDigit: Parser[Double] = error("TODO")

  def plus: Parser[Double] = error("TODO")

  def minus: Parser[Double] = error("TODO")

  def math: Parser[Double] = error("TODO")
}
