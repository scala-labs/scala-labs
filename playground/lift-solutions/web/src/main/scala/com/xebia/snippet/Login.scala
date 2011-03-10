package com.xebia.snippet

import xml.NodeSeq
import net.liftweb.http._
import net.liftweb.util._
import Helpers._
import com.xebia.model.User
import auth.{userRoles, AuthRole}
import com.xebia.session._
import net.liftweb.common.{Empty, Box, Full}

class Login extends StatefulSnippet {

  def dispatch: DispatchIt = {
    case "login" => login _
  }

  var email = ""
  var password = ""


  def login(xhtml: NodeSeq): NodeSeq = {
    def authenticate = {
      val user = User.findByEmail(email)
      user match {
        case Full(user) if (user.password == password) => {
          println("Auth succeeded for " + email)
          UserHolder.setCurrentUser(user)
          userRoles(AuthRole(user.role.name))
          unregisterThisSnippet
          redirectTo("/index")
        }
        case Full(user) => S.error("Wrong password ")
        case _ => S.error("User is unknown")
      }
    }
    bind("entry", xhtml, "email" -> SHtml.text(email, email = _),
      "password" -> SHtml.password(password, password = _),
      "submit" -> SHtml.submit("Login", authenticate _))
  }


}