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
import _root_.org.apache.commons.httpclient.auth._

class OnlineTwitter extends TwitterApi {    

	def publicTimeline(): Seq[TwitterStatus] = {

		val publicFeedURL = "http://twitter.com/statuses/public_timeline.xml"

		val client:HttpClient = new HttpClient()
		val method = new GetMethod(publicFeedURL)
		client.executeMethod(method)
		val xml = XML.loadString(new String(method.getResponseBody()))
		(xml \\ "status").elements.toList.map(s => TwitterStatus.fromXml(s))
	}

	def userTimeline():Seq[TwitterStatus] = {
		User.currentUser match {
			case Full(User) => getUserTimeline(User.currentUser.open_!)
			case _ => Nil
		}
	}

	private def getUserTimeline(user:User): Seq[TwitterStatus] = {
		val userTimelineURL = "http://www.twitter.com/statuses/user_timeline.xml?count=40"
		val defaultcreds = new UsernamePasswordCredentials(user.email, user.password);
		val client:HttpClient = new HttpClient()
		client.getState().setCredentials(AuthScope.ANY, defaultcreds);
		val method = new GetMethod(userTimelineURL)
		method.setDoAuthentication(true)
		client.executeMethod(method)
		val xml = XML.loadString(new String(method.getResponseBody()))
		(xml \\ "status").elements.toList.map(s => TwitterStatus.fromXml(s))
	}
}
