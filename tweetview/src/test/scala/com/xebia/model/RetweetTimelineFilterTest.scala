package com.xebia.model

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

import _root_.scala.xml.XML
import _root_.net.liftweb.util._

class RetweetTimelineFilterTest {

	var statuses = List[TwitterStatus]()

	@Before
	def initialize {
		val timeline = XML.load(this.getClass.getResourceAsStream("/timeline-with-multiple-retweets.xml"))
        statuses = (timeline \\ "status").elements.toList.map(s => TwitterStatus.fromXml(s))

		println(statuses(2))
	}

    @Test
    def example = {
		assertEquals(20, statuses.length)
	}

	@Test def mapToRetweetIfPossiblePossible    = assertEquals(RetweetTimelineFilter.RETWEET_ID, RetweetTimelineFilter.mapToRetweetIfPossible(statuses(2)).id)

	@Test def mapToRetweetIfPossibleNotPossible = assertNotSame(RetweetTimelineFilter.RETWEET_ID, RetweetTimelineFilter.mapToRetweetIfPossible(statuses(0)).id)

	@Test def createRetweet = {
		val retweetStatus = RetweetTimelineFilter.createRetweet(statuses(2), "puredanger", "text")
		assertEquals("puredanger", retweetStatus.user.name)
	}

	@Test def filter = {
		println(statuses.map(_.id).mkString(", "))
		println("Length: " + statuses.length)

		val filteredStatuses = RetweetTimelineFilter.filter(statuses)

		println(filteredStatuses.map(_.id).mkString(", "))
		println("Length: " + filteredStatuses.length)

		assert(true)
	}
}
