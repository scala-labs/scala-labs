package com.xebia.model

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import _root_.scala.xml.XML
import _root_.net.liftweb.util._

class RetweetFilterTest {

	val twitterTimeline = TwitterTimeline.fromXML(XML.load(this.getClass.getResourceAsStream("/timeline-with-multiple-retweets.xml")))

	@Test def filter = {
		assertEquals(20, twitterTimeline.numberOfStatuses)
		val filteredStatuses = RetweetFilter.filter(twitterTimeline)
		assertEquals(18, filteredStatuses.numberOfStatuses)
	}
}
