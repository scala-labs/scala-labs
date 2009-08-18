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

class Login extends StatefulSnippet {
  def dispatch = {
    case "login" => loginUser _
  }

    var userId:String = "";    
    
    def loginUser (xhtml : NodeSeq) : NodeSeq = {
         def auth() = {S.setSessionAttribute("userId", userId)
            S.redirectTo("/mytimeline")
         }
         
          bind("login", xhtml,
                "userId" -> SHtml.text("", userId = _, "maxlength" -> "40"),
                "submit" -> SHtml.submit("Login", auth))
    }
}


