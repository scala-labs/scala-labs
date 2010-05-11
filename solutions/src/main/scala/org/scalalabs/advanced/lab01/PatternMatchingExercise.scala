package org.scalalabs.advanced.lab01

import scala.xml._
import collection.mutable.{ListBuffer => MList}
import scala.None

/**
 * Scala's pattern matching features are very powerful. Learn
 * how to unleash that power by solving the exercises
 * contained in this class. The exercises cover the following topics:
 * - argument extractors
 * - regexp matching
 * - xml matching
 */
object PatternMatchingExercise {

  /*************************************************************************
   * CUSTOM ARGUMENT EXTRACTORS
   *************************************************************************/

  /**
   * Write a simple argument extractor, which
   * extracts filename and extension. Basically,
   * implement an apply and unapply method.
   * Example:
   * MyNotes.txt
   * -> MyNotes txt
   */
  object FileName {
    def apply(name:String, extenstion:String) {
      name + "." + extenstion
    }
    def unapply(name:String):Option[(String, String)] = {
      val parts = name.split("\\.")
      if(parts.length == 2) Some(parts(0), parts(1)) else None
    }
  }

  /**
   * Write a variable argument extractor, which
   * extracts the elements of a path in reverse order.
   * Basically implement a unapplySeq method
   * Example:
   * /home/anyuser/development/scala/
   * -> scala development anyuser home
   */
  object Path {
    def unapplySeq(path:String):Option[Seq[String]] = {
      val parts = path.split("/").toList
      if(parts.length > 0) parts match {
        case "" :: tail => Some(tail.reverse)
        case _ => Some(parts.reverse)
      } else None
    }
  }

  /**
   * Combine the FileName and Path extractor in a match expression to
   * retrieve the filename of a absolute path, e.g.
   * /home/anyuser/development/scala/AdvancedPatternMatchingTest.scala
   * -> AdvancedPatternMatchingTest
   */
  def fileNameRetriever(path:String) = {
    path match {
      case Path(FileName(name, _), _*) => name
      case _ => "No match"
    }
  }

  /*************************************************************************
   * REGEXP MATCHING
   *************************************************************************/

  /**
   * Define a regexp to match properties the following properties of a performance log-line
   * <date>;PRF;server<number>;<id>;Processing took <amount> ms
   * E.g.:
   * 2010-04-08T04:08:05.889Z;PRF;server1;1004080608005100002;Processing took 200 ms
   */
  val PerfLogLineRE = """^(.{24});PRF;server(\d);(\d+);.+\s(\d+).*""".r

  /**
   * Define a regexp and use the 'loop' function to iterate over a piece of text
   * in order to retrieve phonenumbers in the format: [(]000[)-]0000000. Characters
   * between [] are optional. Example
   * For marketing call 040-2920029, for sales: 0402920029 for finance: (040)2920029
   * -> 040-2920029, 0402920029, (040)2920029
   */
  def phoneNumberRetriever(phoneNumberText:String):List[String] = {
    (for(line:String <- PhoneNumberRE findAllIn phoneNumberText) yield line).toList
  }

  val PhoneNumberRE = """(\(?\d{3}[-\)]?\d{7})""".r


  /*************************************************************************
   * XML MATCHING
   *************************************************************************/

  /**
   * Take a look at the movies.xml. Use xml matching to extract all genres.
   * 	<Movie>
   * 		<Genre>Comedy</Genre>
   * Store the extracted genre-values in a mutable List in order for
   * the unittest to succeed. It is advisable to make use of the
   * movieNodeProcessor method, which functions as a template that loops over all
   * <movie> nodes. Use the function parameter of the movieNodeProcessor
   * method to implement your solution.
   */
  def filterAllGenres():List[String] = {
    val genreFilterFunction = (xml:Node, capturer:MList[String]) => {
      xml match {
        case <Genre>{genre}</Genre> => capturer += genre.text
        case _ =>
      }
    }
    movieNodeProcessor(genreFilterFunction )
  }


  /**
   * Take a look at the movies.xml. Use xml matching to extract all actors
   * whose names start with the letter 'G'.
   * 	<Movie>
   * 		...
   *  <Actors>
   *    <Actor>Joseph Fiennes</Actor>
   *    <Actor>Gwyneth Paltrow</Actor>
   *    <Actor>Geoffrey Rush</Actor>
   *  </Actors>
   *  ...
   * Store the extracted actorname-values in a mutable List in order for
   * the unittest to succeed. It is advisable to make use of the
   * movieNodeProcessor method, which functions as a template that loops over all
   * <movie> nodes. Use the function parameter of the movieNodeProcessor
   * method to implement your solution.
   */
  def filterActorsStartingWithG():List[String] = {
    val actorsFilterFunction = (xml:Node, capturer:MList[String]) => {
      xml match {
        case <Actors>{actors @ _* }</Actors> =>
          for(<Actor>{actor @ _*}</Actor> <- actors) if(actor.text.startsWith("G"))  {capturer += actor.text }
        case _ =>
      }
    }
    movieNodeProcessor(actorsFilterFunction);
  }

  /**
   * Take a look at the movies.xml. Use xml matching to extract all movies
   * with the top10 attribute set to true.
   * 	<Movie>                         
   *    <Title top10="true">Ocean's 13</Title>
   *  ...
   * Store the extracted actorname-values in a mutable List in order for
   * the unittest to succeed. It is advisable to make use of the
   * movieNodeProcessor method, which functions as a template that loops over all
   * <movie> nodes. Use the function parameter of the movieNodeProcessor
   * method to implement your solution.
   */
  def filterTop10Titles():List[String] = {
    val titleFilterFunction = (xml:Node, capturer:MList[String]) => {
      xml match {
        case title @ <Title>{ _*}</Title> if((title \ "@top10").text == "true") => capturer += title.text
        case _ =>
      }
    }
    movieNodeProcessor(titleFilterFunction)
  }

  /**
   * Use xml matching to recursively extract all text nodes of a random xml.
   * For the sake of simplicity use the movies.xml file as input xml.
   * Store the extracted text nodes in a mutable List in order for
   * the unittest to succeed. Preferrably, provide your solution in the
   * textNodeMatcher method.
   */
  def recursivelyExtractAllTextNodes():List[String] = {
    var capturer = new MList[String]()
    textNodeMatcher(getXML, capturer)
    capturer.toList
  }

  private def textNodeMatcher(node:NodeSeq, capturer:MList[String]):Unit = {
    def isTextNode(node:Node) = (node.child.size == 1 && node.text.length > 0)
    node match {
      case txtNode:Node if(isTextNode(txtNode)) => capturer += txtNode.text;
      case node:Node => textNodeMatcher(node \ "_", capturer)
      case seq:NodeSeq => for(node <- seq) textNodeMatcher(node, capturer)
    }
  }


  /*------------------------------------------
   * XML MATCHING HELPER METHODS
   ------------------------------------------*/


  private def getXML =XML.load(this.getClass.getResourceAsStream("/movies.xml"))

  private def movieNodeProcessor(filter:(Node, MList[String]) => Unit):List[String] = {
    var capturer = new MList[String]()
    for(movieNode <- getXML \\ "Movie" \ "_") {
      filter(movieNode, capturer)
   }
   capturer.toList
  }


}