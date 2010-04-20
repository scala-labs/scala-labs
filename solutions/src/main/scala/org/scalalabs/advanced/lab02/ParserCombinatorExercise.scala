package org.scalalabs.advanced.lab02

import _root_.scala.util.parsing.combinator.JavaTokenParsers

/**
 * Created by IntelliJ IDEA.
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 1:51:58 PM
 * To change this template use File | Settings | File Templates.
 */

class ParserCombinatorExercise extends JavaTokenParsers {

  def particle : Parser[Any] = "the"
  def noun : Parser[Any] = "fox" | "dog"
  def adjective: Parser[Any] = "quick" | "brown" | "lazy"
  def verb: Parser[Any] = "jumps"
  def preposition: Parser[Any] = "over"

  def nounPhrase : Parser[Any] = particle~rep(adjective)~noun
  def prepositionPhrase: Parser[Any] = preposition~nounPhrase
  def verbPhrase: Parser[Any] = verb~prepositionPhrase | verb
  def sentence: Parser[Any] = nounPhrase~verbPhrase

}
