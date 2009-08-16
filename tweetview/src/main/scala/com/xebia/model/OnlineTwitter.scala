/*
 * MockTwitter.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

import _root_.scala.xml.XML
import _root_.net.liftweb.util._
import _root_.org.apache.commons.httpclient._
import _root_.org.apache.commons.httpclient.methods._
import _root_.org.apache.commons.httpclient.params._

class OnlineTwitter extends TwitterApi {

    def publicTimeline():Seq[TwitterStatus] = {
      val publicFeedURL = "http://twitter.com/statuses/public_timeline.xml"

      val method = new GetMethod(publicFeedURL)
//      method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false))
      new HttpClient().executeMethod(method)
//      println(new String(method.getResponseBody()))
      val xml = XML.loadString(new String(method.getResponseBody()))
      (xml \\ "status").elements.toList.map(s => TwitterStatus.fromXml(s))
    }

    def timeline(timeline:String):Seq[TwitterStatus] = {
        Nil
    }
}
