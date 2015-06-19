package org.scalalabs.advanced.lab01

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._
import PatternMatchingExercise._

/**
 * @see PatternMatchingExcercise
 */

class PatternMatchingExcerciseTest extends JUnitSuite {

  /**
   * ***********************************************************************
   * CUSTOM ARGUMENT EXTRACTORS
   * ***********************************************************************
   */
  @Test
  def matchFileNameTest() {
    val matchResult = "HelloAdvancedWorldOf.scala" match {
      case FileName(name, extension) => "I match " + name + " of filetype " + extension
      case _ => "No match"
    }
    assert(matchResult == "I match HelloAdvancedWorldOf of filetype scala")
  }

  @Test
  def matchElementsInPathTest() {
    val matchResult = "/home/anyuser/development/scala/" match {
      case Path(first, _, _, last) => "The path starts with " + first + " and ends with " + last
      case _ => "No match"
    }
    assert(matchResult == "The path starts with scala and ends with home")
  }

  @Test
  def matchFileNameInPathTest() {
    val matchResult = fileNameRetriever("/home/anyuser/development/scala/HelloAdvancedWorldOf.scala")
    assert(matchResult == "HelloAdvancedWorldOf")
  }

  /**
   * ***********************************************************************
   * REGEXP MATCHING
   * ***********************************************************************
   */

  @Test
  def regexLogLineMatchTest() {
    val matchResult = "2010-04-08T04:08:05.889Z;PRF;server1;1004080608005100002;Processing took 200 ms" match {
      case PerfLogLineRE(date, server, threadId, ms) => (date :: server :: threadId :: ms :: Nil).mkString("|")
      case _ => "No match"
    }
    assert(matchResult == "2010-04-08T04:08:05.889Z|1|1004080608005100002|200")
  }

  @Test
  def regexMultiplePhoneNumberMatchTest() {
    val phoneNumberText = "For marketing call 040-2920029, for sales: 0402920029 for finance: (040)2920029"
    val result = phoneNumberRetriever(phoneNumberText)

    assert("040-2920029" :: "0402920029" :: "(040)2920029" :: Nil == result)
  }

  /**
   * ***********************************************************************
   * XML MATCHING
   * ***********************************************************************
   */

  @Test
  def xmlMatchAllGenres() {
    val result = filterAllGenres
    assert("Comedy" :: "Action" :: Nil == result)
  }

  @Test
  def xmlMatchAllTop10Titles() {
    val result = filterTop10Titles
    assert("Ocean's 13" :: Nil == result)
  }

  @Test
  def xmlMatchAllActorsStartingWithG() {
    val result = filterActorsStartingWithG
    assert("Gwyneth Paltrow" :: "Geoffrey Rush" :: Nil == result)
  }

  @Test
  def xmlMatchAllTextNodes() {
    val result = recursivelyExtractAllTextNodes
    assert("Ocean's 13" :: "Comedy" :: "2006" :: "Joseph Fiennes" :: "Gwyneth Paltrow" :: "Geoffrey Rush" :: "John Madden" :: "USA" :: "Robin Hood" :: "Action" :: "2010" :: "Mark Strong" :: "Russell Crowe" :: "Cate Blanchett" :: "Ridley Scott" :: "UK" :: Nil == result)

  }

}