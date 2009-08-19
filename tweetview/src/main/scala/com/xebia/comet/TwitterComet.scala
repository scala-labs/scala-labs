/*
 * TwitterComet.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.comet

import _root_.com.xebia.model._
import _root_.com.xebia.config._
import _root_.java.util.Date
import _root_.net.liftweb.http.js.JsCmds._
import _root_.scala.xml._
import _root_.net.liftweb.http._
import _root_.net.liftweb.util._
import S._
import SHtml._
import Helpers._


class TwitterCometActor extends CometActor {

    override def defaultPrefix = Full("ctweet")


    def render = {
        val entries = TwitterClient.client.publicTimeLine.statuses
        bind("time" -> timeSpan,
             "entry" -> <ol>{entries.flatMap(statusView _)}</ol>
        )

    }
    
   def statusView(st: TwitterStatus): NodeSeq = {
    <li>
      "Created at " + {st.createdAt} + ", by: " + {st.user.name} + " content " + {st.text}
    </li>
  }

    def timeSpan = (<span id="time">{timeNow}</span>)
    // schedule a ping every minute so we redraw

    ActorPing.schedule(this, Tick, 60000L)

    override def lowPriority : PartialFunction[Any, Unit] = {
        case Tick => {
           println("Got tick " + new Date());
           partialUpdate(SetHtml("time", Text(timeNow.toString)))
           reRender(false)
           // schedule an update in 1 minute
           ActorPing.schedule(this, Tick, 600000L)
        }
    }

    case object Tick
}
