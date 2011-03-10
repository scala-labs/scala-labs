package com.xebia.snippet

import xml.{NodeSeq, Group}
import net.liftweb.http._
import net.liftweb._
import js._
import JE._
import JsCmds._
import json._
import util.Helpers._
import widgets.autocomplete.AutoComplete
import xml._
import com.xebia.model.User
import com.xebia.utils.Utils._

object UserLinker {
  /*  def ajaxFunc(): JsCmd = {
    JsCrVar("myObject", JsObj(("persons", JsArray(
      JsObj(("name", "Thor"), ("race", "Asgard")),
      JsObj(("name", "Todd"), ("race", "Wraith")),
      JsObj(("name", "Rodney"), ("race", "Human"))
    )))) & JsRaw("alert(myObject.persons[0].name)")
  }

  def renderAjaxButton(xhtml: Group): NodeSeq = {
    bind("ex", xhtml,
      "button" -> SHtml.ajaxButton(Text("Press me"), ajaxFunc _))
  }

   def renderSearchBox(xhtml: Group): NodeSeq = {
    bind("ex", xhtml,
      "button" -> SHtml.ajaxButton(Text("Press me"), ajaxFunc _))
  }*/
  /*
 $(document).ready(function(){
    var data = "Core Selectors Attributes Traversing Manipulation CSS Events Effects Ajax Utilities".split(" ");
$("#example").autocomplete(data);
  });

def createSearchBoxJs = onLoadScript(JsCrVar("data", JsArray(Str("Core"), Str("Selectors"), Str("Attributes"), Str("Traversing"))) & JqId(Str("searchBox").) )
   */


  var userId = ""

  def sample(xhtml: NodeSeq): NodeSeq = {
    def doConnect = {
      val user = User.findById(userId.toLong)
      S.notice("You wil connect to user " + user)
    }

    val formatListItem = AnonFunc("row", JsReturn(JsRaw("""row[0].split(".")[1]"""))).toJsCmd
    val formatResult = JsRaw("""function(data, value) { return value.split(".")[1]; }""").toJsCmd

    bind("f", xhtml,
      "searchBox" -> AutoComplete("", (keyword, limit) => findFuzzyInFirstAndLastName(keyword, limit), (value: String) => { userId = value.split("\\.").head;println ("Submitted: " + value + " " + value.split("\\.").head) } , List(("formatItem", formatListItem), ("formatResult", formatResult))),
        "submit" -> SHtml.submit("Connect", doConnect _ )
    )
  }

  def findFuzzyInFirstAndLastName(keyword: String, maxResult: Int): List[String] = {
    User.findFuzzyInFirstAndLastName(keyword, maxResult).map(u => u.id + "." + u.firstName + " " + u.lastName)
  }


  /*
private val data = List(
    "Timothy", "Derek", "Ross", "Tyler",
    "Indrajit", "Harry", "Greg", "Debby")


      //"searchBox" -> AutoComplete("", (current,limit) => data.filter(_.toLowerCase.startsWith(current.toLowerCase)),value => println("Submitted: " + value))
      //      "searchBox" -> AutoComplete("", (keyword,limit) => findFuzzyInFirstAndLastName(keyword, limit),value => println("Submitted: " + value))


    val formatingListitemJs = JsRaw("""return function(data, i, n, value) {""" +
    """if (value.split(".").length > 1) {""" +
    """return "<img src='service/userimage/" + value.split(".")[0] + "' height='40' width='30'/> " + value.split(".")[1];""" +
    """} else { return value }"""+
    """}""").toJsCmd

    val formatingListitemJs1 = JsRaw("""function(row) { return "<img src='service/userimage/" + row[0].split(".")[0] + "' height='35' width='28'/>  "  + row[0].split(".")[1]; }""").toJsCmd
    val formatingListitemJs2 = JsRaw("""function(row) { return row[0].split(".")[1]; }""").toJsCmd
//    val formatingListitemJs3 = AnonFunc("row", JsRaw("""placeimg(row[0].split(".")[0])""") & JsReturn(JsRaw("""row[0].split(".")[1]"""))).toJsCmd
    val formatingListitemJs3 = AnonFunc("row", JsReturn(JsRaw("""row[0].split(".")[1]"""))).toJsCmd
    val formatResult = JsRaw("""function(data, value) { placeimg(row[0].split(".")[0]);return value.split(".")[1]; }""").toJsCmd


   */
}