/*
 * MockTwitter.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model


import com.xebia.config._

import _root_.scala.xml.XML
import _root_.net.liftweb.util._
import _root_.org.apache.commons.httpclient._
import _root_.org.apache.commons.httpclient.methods._
import _root_.org.apache.commons.httpclient.params._
import _root_.org.apache.commons.httpclient.auth._

import _root_.scala.xml.XML

class OnlineTwitter extends TwitterApi {    

    def publicTimeline: TwitterTimeline = {

		val publicFeedURL = "http://twitter.com/statuses/public_timeline.xml"

		val client:HttpClient = new HttpClient()
		val method = new GetMethod(publicFeedURL)
		client.executeMethod(method)
		TwitterTimeline.fromXML(XML.loadString(new String(method.getResponseBody())))
    }

    def userTimeline(user:TweetviewUser): TwitterTimeline = {
        val userTimelineURL = "http://www.twitter.com/status/user_timeline/" + user.userId + ".xml"
        println("Getting timeline for: " + userTimelineURL)
		//        val defaultcreds = new UsernamePasswordCredentials(user.email, user.password);
        val client:HttpClient = new HttpClient()
		//        client.getState().setCredentials(AuthScope.ANY, defaultcreds);
        val method = new GetMethod(userTimelineURL)
		//        method.setDoAuthentication(true)
        client.executeMethod(method)
		TwitterTimeline.fromXML(XML.loadString(new String(method.getResponseBody())))
    }
}
