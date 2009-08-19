package com.xebia.model

import com.xebia.config._
import _root_.scala.xml.XML
import _root_.net.liftweb.util._

class OfflineTwitter extends TwitterApi {

    def publicTimeline():Seq[TwitterStatus] = {
        val xml = XML.load(this.getClass.getResourceAsStream("/timeline-with-multiple-retweets.xml"))
        val statuses = xml \\ "status"
        statuses.elements.toList.map(s => TwitterStatus.fromXml(s))
    }

    def userTimeline(user:TweetviewUser):Seq[TwitterStatus] = {
        publicTimeline()
    }
}
