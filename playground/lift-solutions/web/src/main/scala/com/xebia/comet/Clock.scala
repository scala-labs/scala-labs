package com.xebia.comet

import xml._
import net.liftweb._
import http._
import common._
import util._
import util.TimeHelpers._
import net.liftweb.http.js.JsCmds._

import _root_.java.util.Date
import actors._

class Clock extends CometActor {
  override def defaultPrefix = Full("clk")

  def render = bind("time" -> timeSpan)

  def timeSpan = (<span id="time">{timeNow}</span>)


  println( "==========================ACTOR STARTED============================================")

  // schedule a ping every 10 seconds so we redraw
  ActorPing.schedule(this, Tick, 10000L)
  //ActorPing.schedule(this, Tick, 10000L)

  override def lowPriority: PartialFunction[Any, Unit] = {
    case Tick => {
      //println("Got tick " + new Date());
      partialUpdate(SetHtml("time", Text(timeNow.toString)))
      // schedule an update in 10 seconds
      ActorPing .schedule(this, Tick, 10000L)
//      ActorPing.schedule(this, Tick, 10000L)
    }
  }
}

case object Tick