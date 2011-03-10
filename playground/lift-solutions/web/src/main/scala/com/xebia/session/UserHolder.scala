package com.xebia.session

import com.xebia.model.User
import net.liftweb.http.{RequestVar, SessionVar}

object UserHolder {
  private object currentSessionUser extends SessionVar[User](null)
  private object currentReqUser extends RequestVar[User](null)


  def currentUser:User = if(currentSessionUser.get != null) currentSessionUser.get else currentReqUser.get

  def setCurrentUser(user:User) {
   currentReqUser(user)
    currentSessionUser(user)
  }

  def loggedIn = currentSessionUser.is != null

  def logout = currentSessionUser(null);currentReqUser(null)

}