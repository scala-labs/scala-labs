package org.scalalabs.advanced.lab01

import org.scalalabs.advanced.lab01.PatternMatchingExercise._
import org.specs2.mutable.Specification

/**
 * @see PatternMatchingExcercise
 */
class PatternMatchingExcerciseTest extends Specification {

  "Custom argument extractors" should {
    "match file name" in {
      val matchResult = "HelloAdvancedWorldOf.scala" match {
        case FileName(name, extension) =>
          "I match " + name + " of filetype " + extension
        case _ => "No match"
      }
      matchResult ==== "I match HelloAdvancedWorldOf of filetype scala"
    }

    "match element in path" in {
      val matchResult = "/home/anyuser/development/scala/" match {
        case Path(first, _, _, last) =>
          "The path starts with " + first + " and ends with " + last
        case _ => "No match"
      }
      matchResult ==== "The path starts with scala and ends with home"
    }

    "match file name in path" in {
      val matchResult = fileNameRetriever(
        "/home/anyuser/development/scala/HelloAdvancedWorldOf.scala")
      matchResult ==== "HelloAdvancedWorldOf"
    }
  }

  "regexp matching" should {
    "regex log line match" in {
      val matchResult =
        "2010-04-08T04:08:05.889Z;PRF;server1;1004080608005100002;Processing took 200 ms" match {
          case PerfLogLineRE(date, server, threadId, ms) =>
            (date :: server :: threadId :: ms :: Nil).mkString("|")
          case _ => "No match"
        }
      matchResult ==== "2010-04-08T04:08:05.889Z|1|1004080608005100002|200"
    }
    "regex multiple phone number matches" in {
      val phoneNumberText =
        "For marketing call 040-2920029, for sales: 0402920029 for finance: (040)2920029"
      val result = phoneNumberRetriever(phoneNumberText)

      "040-2920029" :: "0402920029" :: "(040)2920029" :: Nil ==== result
    }
  }

  "xml matching" should {
    "xml match all genres" in {
      val result = filterAllGenres
      "Comedy" :: "Action" :: Nil ==== result
    }

    "xml match all top 10 titles" in {
      val result = filterTop10Titles
      "Ocean's 13" :: Nil ==== result
    }

    "xml match all actors starting with G" in {
      val result = filterActorsStartingWithG
      "Gwyneth Paltrow" :: "Geoffrey Rush" :: Nil ==== result
    }

    "xml match all text nodes" in {
      val result = recursivelyExtractAllTextNodes
      "Ocean's 13" :: "Comedy" :: "2006" :: "Joseph Fiennes" :: "Gwyneth Paltrow" :: "Geoffrey Rush" :: "John Madden" :: "USA" :: "Robin Hood" :: "Action" :: "2010" :: "Mark Strong" :: "Russell Crowe" :: "Cate Blanchett" :: "Ridley Scott" :: "UK" :: Nil ==== result
    }
  }
}
