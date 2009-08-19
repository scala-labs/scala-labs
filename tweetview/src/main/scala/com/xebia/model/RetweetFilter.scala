package com.xebia.model

import scala.util.matching.Regex

import scala.collection.mutable.Set

object RetweetFilter extends TweetFilter {

	val RETWEET_EXPRESSION = """^.*RT ?@([\S]*):?\s(.*)$""".r

	def filter(timeline: TwitterTimeline): TwitterTimeline = {
		var retweetTexts = Set.empty[String]
		new TwitterTimeline(timeline.statuses.map(transformRetweetIfPossible(_)).filter(notDuplicateRetweets(_, retweetTexts)))
	}

	private def transformRetweetIfPossible(status: TwitterStatus) = status.text match {
		case RETWEET_EXPRESSION(retweetUser, retweetText) => TwitterStatus.fromRetweet(status, retweetUser, retweetText)
		case _ => status
	}

	private def notDuplicateRetweets(status: TwitterStatus, retweetTexts: Set[String]) = {
		if (status.isRetweet) {
			if (retweetTexts.contains(statusTextForComparison(status))) false
			else {
				retweetTexts += statusTextForComparison(status)
				true
			}
		}
		else true
	}

	private def statusTextForComparison(status: TwitterStatus) = status.text.replaceAll("""\s""", "")
}
