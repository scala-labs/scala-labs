package org.scalalabs.advanced.lab02

import org.specs2.mutable.Specification

/** @see
  *   ParserCombinatorExerciseTest
  */
class ParserCombinatorExerciseTest extends Specification {

  val parser = new ParserCombinatorExercise()
  import parser._

  "parser combinator exercise" should {
    "parse noun phrase" in {
      parseAll(nounPhrase, "the fox").successful should beTrue
      parseAll(nounPhrase, "the brown fox").successful should beTrue
      parseAll(nounPhrase, "the dog").successful should beTrue
      parseAll(nounPhrase, "the brown quick dog").successful should beTrue

      parseAll(nounPhrase, "dog").successful should beFalse
      parseAll(nounPhrase, "the quick brown").successful should beFalse
      parseAll(nounPhrase, "brown dog").successful should beFalse
      parseAll(nounPhrase, "quick").successful should beFalse
    }

    "parse preposition phrase" in {
      parseAll(prepositionPhrase, "over the fox").successful should beTrue
      parseAll(prepositionPhrase, "over the brown fox").successful should beTrue
      parseAll(prepositionPhrase, "over the dog").successful should beTrue
      parseAll(
        prepositionPhrase,
        "over the lazy quick dog"
      ).successful should beTrue

      parseAll(prepositionPhrase, "over dog").successful should beFalse
      parseAll(
        prepositionPhrase,
        "the quick brown fox"
      ).successful should beFalse
      parseAll(prepositionPhrase, "over brown dog").successful should beFalse
      parseAll(
        prepositionPhrase,
        "over the dog brown"
      ).successful should beFalse
    }

    "parse verb phrase" in {
      parseAll(verbPhrase, "jumps over the fox").successful should beTrue
      parseAll(verbPhrase, "jumps").successful should beTrue
      parseAll(verbPhrase, "jumps the dog").successful should beTrue
      parseAll(verbPhrase, "jumps over the lazy fox").successful should beTrue

      parseAll(verbPhrase, "jumps over dog").successful should beFalse
      parseAll(verbPhrase, "the quick brown fox").successful should beFalse
      parseAll(verbPhrase, "jumps over brown the dog").successful should beFalse
      parseAll(verbPhrase, "jumps over the quick").successful should beFalse
    }

    "parse sentence" in {
      parseAll(sentence, "the fox jumps").successful should beTrue
      parseAll(sentence, "the quick fox jumps").successful should beTrue
      parseAll(sentence, "the quick brown fox jumps").successful should beTrue
      parseAll(sentence, "the fox jumps over the dog").successful should beTrue
      parseAll(
        sentence,
        "the quick dog jumps the lazy dog"
      ).successful should beTrue
      parseAll(
        sentence,
        "the quick brown fox jumps over the lazy dog"
      ).successful should beTrue

      parseAll(
        sentence,
        "the quick brown fox jumps over dog"
      ).successful should beFalse
      parseAll(
        sentence,
        "fox jumps over the lazy dog"
      ).successful should beFalse
      parseAll(
        sentence,
        "quick the brown fox jumps over the lazy dog"
      ).successful should beFalse
      parseAll(
        sentence,
        "jumps the quick brown fox over the lazy dog"
      ).successful should beFalse
      parseAll(
        sentence,
        "the quick brown fox jumps over the lazy"
      ).successful should beFalse
      parseAll(
        sentence,
        "the quick brown jumps fox over the lazy dog"
      ).successful should beFalse
    }

    "parse single digit" in {
      parseAll(parsedDigit, "2").get ==== 2.0
      parseAll(parsedDigit, "-456").get ==== -456.0
      parseAll(parsedDigit, "2.078967").get ==== 2.078967
    }

    "parse one addition" in {
      parseAll(plus, "2 + 10").get ==== 12.0
      parseAll(plus, "-15 + 78").get ==== 63.0
      parseAll(plus, "1.3 + 6.2").get ==== 7.5
    }

    "parse single subtraction" in {
      parseAll(minus, "2 - 50").get ==== -48.0
      parseAll(minus, "7.9 - 6").get should be ~ (1.9, 0.0001)
      parseAll(minus, "-64 - 3.853").get ==== -67.853
    }

    "parse multiple additions" in {
      parseAll(math, "2 + 10 + 34 + 5").get ==== 51.0
      parseAll(math, "1.3 + 6.2 + 1.6 + 87.256").get ==== 96.356
      parseAll(
        math,
        "6.3 + 200 + 9.0 + 8 + 0.2257 + 15 + 0 + 56"
      ).get should be ~ (294.5257, 0.0001)
    }

    "parse simple arithmetic" in {
      parseAll(math, "-2 - 10 + 34 - 5").get ==== -41.0
      parseAll(math, "1.3 + 6.2 - 1.6 + 87.256").get ==== -81.356
      parseAll(
        math,
        "-6.3 + 200 + 9.0 - 8 + 0.2257 + 15 + 0 - 56"
      ).get should be ~ (235.4743, 0.0001)
      parseAll(math, "56").get ==== 56
    }
  }
}
