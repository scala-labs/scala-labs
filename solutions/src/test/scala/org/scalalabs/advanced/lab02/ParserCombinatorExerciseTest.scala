package org.scalalabs.advanced.lab02

import org.junit.Test
import org.junit.Assert._

/**
 * Created by IntelliJ IDEA.
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 2:42:55 PM
 * To change this template use File | Settings | File Templates.
 */

class ParserCombinatorExerciseTest extends ParserCombinatorExercise {

  @Test
  def parseSentence {
    println(parseAll(sentence, "the fox jumps"))
    assertTrue(parseAll(sentence, "the fox jumps").successful)
    assertTrue(parseAll(sentence, "the quick fox jumps").successful)
    assertTrue(parseAll(sentence, "the quick brown fox jumps").successful)
    assertTrue(parseAll(sentence, "the fox jumps over the dog").successful)
    assertTrue(parseAll(sentence, "the quick dog jumps over the lazy dog").successful)
    assertTrue(parseAll(sentence, "the quick brown fox jumps over the lazy dog").successful)

    assertFalse(parseAll(sentence, "the quick brown fox jumps over dog").successful)
    assertFalse(parseAll(sentence, "fox jumps over the lazy dog").successful)
    assertFalse(parseAll(sentence, "quick the brown fox jumps over the lazy dog").successful)
    assertFalse(parseAll(sentence, "jumps the quick brown fox over the lazy dog").successful)
    assertFalse(parseAll(sentence, "the quick brown fox jumps over the lazy").successful)
    assertFalse(parseAll(sentence, "the quick brown jumps fox over the lazy dog").successful)

  }
}