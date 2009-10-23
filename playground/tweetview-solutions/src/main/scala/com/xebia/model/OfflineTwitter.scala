package com.xebia.model

import com.xebia.config._
import _root_.scala.xml.XML
import _root_.net.liftweb.util._

class OfflineTwitter extends TwitterApi {

    def publicTimeline: TwitterTimeline = {
        val xml = XML.load(this.getClass.getResourceAsStream("/timeline-with-multiple-retweets.xml"))
		TwitterTimeline.fromXML(xml)
    }

    def userTimeline(user:TweetviewUser): TwitterTimeline = publicTimeline

    def friendsTimeline(user: TweetviewUser): TwitterTimeline = publicTimeline

    def updateStatus(user:TweetviewUser, t:String): Unit = {}
}
