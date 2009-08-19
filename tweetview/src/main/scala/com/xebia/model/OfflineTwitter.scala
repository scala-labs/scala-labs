package com.xebia.model

import _root_.scala.xml.XML
import _root_.net.liftweb.util._

class OfflineTwitter extends TwitterApi {

    def publicTimeline: TwitterTimeline = {
        val xml = XML.load(this.getClass.getResourceAsStream("/timeline-with-multiple-retweets.xml"))
		TwitterTimeline.fromXML(xml)
    }

    def userTimeline(user:User): TwitterTimeline = publicTimeline
}
