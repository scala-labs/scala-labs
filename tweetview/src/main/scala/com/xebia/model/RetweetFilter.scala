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

	// question: why do retweets all have the same id ? For now, I would suggest reusing the first id

	// alternate impl: put ReTweet subclasses into the list and then call list.removeDuplicates with
    // the equals/hashcode implementation looking for text equality, that way you don't need to keep an extra set

    // if you DO use the empty set, why is it passed into the method ? I don't see where else it is being used

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

	// removed whitespace
	private def statusTextForComparison(status: TwitterStatus) = status.text.replaceAll("""\s""", "")
}
