package com.xebia.snippet

import _root_.scala.xml._
import _root_.net.liftweb.http._
import _root_.net.liftweb.util._
import S._
import SHtml._
import Helpers._

import _root_.com.xebia.model._
import _root_.com.xebia.config._

import java.util.Date

class TwitterTimeline {
  val formatter = new java.text.SimpleDateFormat("yyyy/MM/dd")

    def showPublic (xhtml : NodeSeq) : NodeSeq = {
        val entries = TwitterClient.client.publicTimeLine match {
	case Nil => Text("No public timeline fount") // Add link to create one...
	case tlines => tlines.flatMap({tline =>
	  bind("st", chooseTemplate("status", "entry", xhtml),
	       "createdAt" -> Text(tline.createdAt),
	       "text" -> Text(tline.text),
               "userName" -> Text(tline.user.name))
					 })
      }
      bind("status", xhtml, "entry" -> entries)
    }
    
}


