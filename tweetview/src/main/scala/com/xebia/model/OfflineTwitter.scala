/*
 * MockTwitter.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

import _root_.scala.xml.XML
import _root_.net.liftweb.util._

class OfflineTwitter extends TwitterApi {

    def publicTimeline():Seq[TwitterStatus] = {
        val xml = XML.load(this.getClass.getResourceAsStream("/twitter_public_timeline.xml"))
//        println("Found xml: " + xml)
        val statuses = xml \\ "status"
        statuses.elements.toList.map(s => TwitterStatus.fromXml(s))
    }

    def userTimeline(user:User):Seq[TwitterStatus] = {
        publicTimeline()
    }
}
