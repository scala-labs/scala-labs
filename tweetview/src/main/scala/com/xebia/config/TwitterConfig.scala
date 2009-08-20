/*
 * TwitterConfig.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.config

import com.xebia.model._
import _root_.net.liftweb.util._
import _root_.net.liftweb.http._

trait TwitterConfig {def twitter:TwitterApi}

object MockTwitterConfig extends TwitterConfig {
    lazy val twitter = new MockTwitter
}

object OfflineTwitterConfig extends TwitterConfig {
    lazy val twitter = new OfflineTwitter
}

object OnlineTwitterConfig extends TwitterConfig {
    lazy val twitter = new OnlineTwitter
}

object TwitterClient {
    object currentClient extends SessionVar[TwitterClient](new TwitterClient(OfflineTwitterConfig))

    def onBeginServicing(session: LiftSession, req: Req) {
       Props.get("tweetview.mode") match {
           case Full("online") => println("setting online mode"); currentClient.set(new TwitterClient(OnlineTwitterConfig))
           case _ => println("setting offline mode"); currentClient.set(new TwitterClient(OfflineTwitterConfig))
       }
    }

    def client = currentClient.is
}

