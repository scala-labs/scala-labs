package com.xebia.rest

import com.xebia.snippet.GMaps
import net.liftweb.common._
import net.liftweb._
import http._
import com.xebia.model._
import com.xebia.session._
import com.xebia.comet._
import com.xebia.snippet._

object DispatchRestAPI extends Logger {

  def dispatch: LiftRules.DispatchPF = {
//    case Req("service" :: "searchuser" :: Nil, _, GetRequest) if(isLong(userId)) =>
//      () => handleUserSearchReq()
    case Req("service" :: "userimage" :: userId :: Nil, _, GetRequest) if(isLong(userId)) =>
      () => handleImageReq(userId.toLong)
    case Req("service" :: "userimage" :: Nil, _, GetRequest) =>
      () => handleImageReq()
    case Req("api" :: "position" :: lat :: lng :: Nil, _, GetRequest) =>
      () => handlePositionReq(lat, lng)
    case Req("api" :: "position" :: "kml" :: Nil, _, GetRequest) =>
      () => handleKMLReq() // default to XML
    case Req("api" :: x :: Nil, "", _) =>
      () => Full(BadResponse()) // Everything else fails
  }

  def handlePositionReq(lat: String, lng: String) = {
    val user = User.findById(UserHolder.currentUser.id).get
    val pos = Position(user, lat.toDouble, lng.toDouble)
    user.positions.add(pos)
    User.merge(user)
    PositionServer ! pos
    Full(OkResponse())

  }

//  def handleUserSearchReq():Box[LiftResponse] = {
//    val res =  UserLinker.findFuzzyInFirstAndLastName(S.param("q").dmap("")(_), S.param("limit").dmap(20)(_.toInt))
//    debug("Found " + res)
//    Full(new PlainTextResponse(res))
//  }

  def handleImageReq(): Box[LiftResponse]  = {
    handleImageReq(Some(UserHolder.currentUser))
  }

  def handleImageReq(id: Long): Box[LiftResponse]  = {
    handleImageReq(User.findById(id))
  }

    // reacts to the GET Request
  def handleKMLReq(): Box[LiftResponse] = {
    Full(XmlResponse(<kml>bla</kml>, "application/vnd.google-earth.kml+xml"))
  }

  private def isLong(value: String)= {
    try {
      value.toLong
      true
    } catch {
      case e => false
    }
  }

  private def handleImageReq(u: Option[User]):Box[LiftResponse] = {
    u match {
      case Some(u) if (u.hasPhoto) => Full(InMemoryResponse(u.photo, List("Content-Type" -> UserHolder.currentUser.mimeType), Nil, 200))
      case _ => Full(RedirectResponse("/img/unkown.png"))
    }
  }




}