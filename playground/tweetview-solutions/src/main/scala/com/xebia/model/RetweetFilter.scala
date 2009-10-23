package com.xebia.model

import scala.util.matching.Regex

import scala.collection.mutable.Set

object RetweetFilter extends TweetFilter {

    // if there is a separate RetweetTwitterStatus class (which isn't the case now)
    // then this should be in the apply method
	val RETWEET_EXPRESSION = """^.*RT ?@([\S]*):?\s(.*)$""".r

	def filter(timeline: TwitterTimeline): TwitterTimeline = {
		new TwitterTimeline(removeDuplicateStatuses(timeline.statuses.map(transformRetweetIfPossible(_))))
	}

	private def transformRetweetIfPossible(status: TwitterStatus) = status.text match {
		case RETWEET_EXPRESSION(retweetUser, retweetText) => TwitterStatus.fromRetweet(status, retweetUser, retweetText)
		case _ => status
	}

    // alternative to .removeDeplicates
    private def removeDuplicateStatuses (statuses: List[TwitterStatus]): List[TwitterStatus] = {
        if (statuses.isEmpty)
            statuses
        else
            statuses.head :: removeDuplicateStatuses(statuses.tail.filter(notSameText(_, statuses.head)))
    }

    private def notSameText(status1: TwitterStatus, status2: TwitterStatus) ={
        processTextForComparison(status1.text) != processTextForComparison(status2.text)
    }

	private def processTextForComparison(text: String) = text.replaceAll("""\s""", "")


}
