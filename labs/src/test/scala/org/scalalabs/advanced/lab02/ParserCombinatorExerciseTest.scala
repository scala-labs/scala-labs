package org.scalalabs.advanced.lab02

import org.junit.Test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._

/**
 * See @ParserCombinatorExercise
 */

class ParserCombinatorExerciseTest extends ParserCombinatorExercise {

  @Test
  def parseNounPhrase {
    assertTrue(parseAll(nounPhrase, "the fox").successful)
    assertTrue(parseAll(nounPhrase, "the brown fox").successful)
    assertTrue(parseAll(nounPhrase, "the dog").successful)
    assertTrue(parseAll(nounPhrase, "the brown quick dog").successful)

    assertFalse(parseAll(nounPhrase, "dog").successful)
    assertFalse(parseAll(nounPhrase, "the quick brown").successful)
    assertFalse(parseAll(nounPhrase, "brown dog").successful)
    assertFalse(parseAll(nounPhrase, "quick").successful)
  }

  @Test
  def parsePrepositionPhrase {
    assertTrue(parseAll(prepositionPhrase, "over the fox").successful)
    assertTrue(parseAll(prepositionPhrase, "over the brown fox").successful)
    assertTrue(parseAll(prepositionPhrase, "over the dog").successful)
    assertTrue(parseAll(prepositionPhrase, "over the lazy quick dog").successful)

    assertFalse(parseAll(prepositionPhrase, "over dog").successful)
    assertFalse(parseAll(prepositionPhrase, "the quick brown fox").successful)
    assertFalse(parseAll(prepositionPhrase, "over brown dog").successful)
    assertFalse(parseAll(prepositionPhrase, "over the dog brown").successful)
  }

  @Test
  def parseVerbPhrase {
    assertTrue(parseAll(verbPhrase, "jumps over the fox").successful)
    assertTrue(parseAll(verbPhrase, "jumps").successful)
    assertTrue(parseAll(verbPhrase, "jumps the dog").successful)
    assertTrue(parseAll(verbPhrase, "jumps over the lazy fox").successful)

    assertFalse(parseAll(verbPhrase, "jumps over dog").successful)
    assertFalse(parseAll(verbPhrase, "the quick brown fox").successful)
    assertFalse(parseAll(verbPhrase, "jumps over brown the dog").successful)
    assertFalse(parseAll(verbPhrase, "jumps over the quick").successful)
  }

  @Test
  def parseSentence {
    assertTrue(parseAll(sentence, "the fox jumps").successful)
    assertTrue(parseAll(sentence, "the quick fox jumps").successful)
    assertTrue(parseAll(sentence, "the quick brown fox jumps").successful)
    assertTrue(parseAll(sentence, "the fox jumps over the dog").successful)
    assertTrue(parseAll(sentence, "the quick dog jumps the lazy dog").successful)
    assertTrue(parseAll(sentence, "the quick brown fox jumps over the lazy dog").successful)

    assertFalse(parseAll(sentence, "the quick brown fox jumps over dog").successful)
    assertFalse(parseAll(sentence, "fox jumps over the lazy dog").successful)
    assertFalse(parseAll(sentence, "quick the brown fox jumps over the lazy dog").successful)
    assertFalse(parseAll(sentence, "jumps the quick brown fox over the lazy dog").successful)
    assertFalse(parseAll(sentence, "the quick brown fox jumps over the lazy").successful)
    assertFalse(parseAll(sentence, "the quick brown jumps fox over the lazy dog").successful)
  }

  @Test
  def parseSingleDigit {
    assertEquals(parseAll(parsedDigit, "2").get, 2.0, 0)
    assertEquals(parseAll(parsedDigit, "-456").get, -456.0, 0)
    assertEquals(parseAll(parsedDigit, "2.078967").get, 2.078967, 0)
  }

  @Test
  def parseOneAddition {
    assertEquals(parseAll(plus, "2 + 10").get, 12.0, 0)
    assertEquals(parseAll(plus, "-15 + 78").get, 63.0, 0)
    assertEquals(parseAll(plus, "1.3 + 6.2").get, 7.5, 0)
  }

  @Test
  def parseSingleSubtraction {
    assertEquals(parseAll(minus, "2 - 50").get, -48.0, 0)
    assertEquals(parseAll(minus, "7.9 - 6").get, 1.9, 0.0001)
    assertEquals(parseAll(minus, "-64 - 3.853").get, -67.853, 0)
  }

  @Test
  def parseMultipleAdditions {
    assertEquals(parseAll(math, "2 + 10 + 34 + 5").get, 51.0, 0)
    assertEquals(parseAll(math, "1.3 + 6.2 + 1.6 + 87.256").get, 96.356, 0)
    assertEquals(parseAll(math, "6.3 + 200 + 9.0 + 8 + 0.2257 + 15 + 0 + 56").get, 294.5257, 0.0001)
  }

  @Test
  def parseSimpleArithmetic {
    assertEquals(parseAll(math, "-2 - 10 + 34 - 5").get, -41.0, 0)
    assertEquals(parseAll(math, "1.3 + 6.2 - 1.6 + 87.256").get, -81.356, 0)
    assertEquals(parseAll(math, "-6.3 + 200 + 9.0 - 8 + 0.2257 + 15 + 0 - 56").get, 235.4743, 0.0001)
    assertEquals(parseAll(math, "56").get, 56, 0)
  }
}
