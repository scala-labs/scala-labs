/*
 * TwitterStatusTest.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import _root_.scala.xml.XML
import _root_.net.liftweb.util._


class TwitterStatusTest {

	var statuses = List[TwitterStatus]()

	@Before
	def initialize {
		val timeline = XML.load(this.getClass.getResourceAsStream("/timeline-with-multiple-retweets.xml"))
        statuses = (timeline \\ "status").elements.toList.map(s => TwitterStatus.fromXml(s))

		println(statuses(2))
	}

    @Test def fromRetweet() = {
		val retweetStatus = TwitterStatus.fromRetweet(statuses(2), "puredanger", "text")
		assertEquals("puredanger", retweetStatus.user.name)
	}

}
