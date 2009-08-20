package com.xebia.model

import java.util.concurrent.ConcurrentHashMap
import _root_.net.liftweb.util._
import _root_.net.liftweb.http._


object LoginState {
  object primaryKey extends SessionVar[Box[String]](Empty) 
  object currentUser extends SessionVar[Box[TweetviewUser]](Empty)

  def onBeginServicing(session: LiftSession, req: Req) {
     (for (u <- getUser) yield {logUserIn(u)}).openOr(logUserIn(DummyUser.dummy))
  }

  def getUser():Box[TweetviewUser] = {
      for {userId <- Props.get("user.id");
          passwd <- Props.get("user.passwd");
          email <- Props.get("user.email")
     } yield (new TweetviewUser(userId, passwd, email))
  }


  def logUserIn(u: TweetviewUser) {    
    primaryKey.set(Full(u.userId))
    currentUser.set(Full(u))
  }

  def logUserOut() {
    currentUser.remove()
    primaryKey.remove()
    S.request.foreach(_.request.getSession.invalidate)
  }

  def loggedIn_? = primaryKey.is.isDefined
}

case class TweetviewUser(val userId:String, val passwd:String, val email:String)
