package org.scalalabs.advanced.lab02

/**
 * Created by IntelliJ IDEA.
 * User: lieke
 * Date: Apr 9, 2010
 */

class ControlStructureExercise(val list : List[String]) {

  private def stringsMatching(matcher: String => Boolean) = {
    for (string <- list; if matcher(string))
      yield string
  }
  
  def stringsEnding(query: String) = stringsMatching(_.endsWith(query))
  def stringsContaining(query: String) = stringsMatching(_.contains(query))

}



