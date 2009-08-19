package com.xebia.model

import scala.util.matching.Regex

import scala.collection.mutable.Set

object RetweetFilter extends TweetFilter {

	val RETWEET_EXPRESSION = """^.*RT ?@([\S]*):?\s(.*)$""".r

	def filter(timeline: TwitterTimeline): TwitterTimeline = {
		var retweetTexts = Set.empty[String]
		new TwitterTimeline(removeDuplicateStatuses(timeline.statuses.map(transformRetweetIfPossible(_))))
	}

	private def transformRetweetIfPossible(status: TwitterStatus) = status.text match {
		case RETWEET_EXPRESSION(retweetUser, retweetText) => TwitterStatus.fromRetweet(status, retweetUser, retweetText)
		case _ => status
	}

	// question: why do retweets all have the same id ? For now, I would suggest reusing the first id
    //
    // answer: why not? We don't have a real id, so showning that clearly doensn't sound bad to me. Maybe make it a negative.

	// alternate impl: put ReTweet subclasses into the list and then call list.removeDuplicates with
    // the equals/hashcode implementation looking for text equality, that way you don't need to keep an extra set
    //
    // answer: I don't like to put this not generic equals method in the twitterstatus. But it is completely reworked anyway
    // thanks to Arjan

    // if you DO use the empty set, why is it passed into the method ? I don't see where else it is being used
    //
    // You should look closer then :)

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
