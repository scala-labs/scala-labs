package com.xebia.comet

import net.liftweb.actor.LiftActor
import net.liftweb.http.{CometActor, CometListener, ListenerManager}
import com.xebia.session.UserHolder._
import com.xebia.snippet._
import _root_.java.util.Date
import com.xebia.model.Position
import net.liftweb.common.{Empty, Logger}

object PositionServer extends LiftActor with ListenerManager with Logger {

  /**
   * This method returns the payload sent to all listening actors
   * when the updateListeners() method is called
   */
  def createUpdate = Empty

  override def lowPriority = {
    case p: Position => {
      updateListeners(p)
    }
    case m => warn("Server received unkown message" + m)
  }
}

class PositionActor extends CometActor with CometListener with Logger{
  def registerWith = PositionServer

  override def lowPriority = {
    case p: Position if (currentUser.id == p.user.id) => {
      partialUpdate(GMaps.updateMapWithPoint(p))
    }
    case p: Position => {
      debug(p + " not for current user " + currentUser)
    }
    case m => {
      warn("Actor received unknown message " + m)
    }
  }

  def render = GMaps.initMap


}
