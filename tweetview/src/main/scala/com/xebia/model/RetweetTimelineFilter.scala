package com.xebia.model

import scala.util.matching.Regex

import scala.collection.mutable.Set

object RetweetTimelineFilter {

	val RETWEET_ID = 999L

	val RETWEET_EXPRESSION = """^.*RT ?@([\S]*):?\s(.*)$""".r

	def filter(twitterStatuses: Seq[TwitterStatus]): Seq[TwitterStatus] = {
		var retweetTexts = Set.empty[String]
		twitterStatuses.map(mapToRetweetIfPossible(_)).filter(notDuplicateRetweets(_, retweetTexts))
	}

	def mapToRetweetIfPossible(status: TwitterStatus) = status.text match {
		case RETWEET_EXPRESSION(retweetUser, retweetText) => createRetweet(status, retweetUser, retweetText)
		case _ => status
	}

	def notDuplicateRetweets(status: TwitterStatus, retweetTexts: Set[String]) = {
		if (isRetweet(status)) {
			if (retweetTexts.contains(status.text.replaceAll("""\s""", ""))) false
			else {
				retweetTexts += status.text.replaceAll("""\s""", "")
				true
			}
		}
		else true
	}

	// TODO: use implicit conversion on TwitterStatus
	def isRetweet(status: TwitterStatus) = 999L == status.id

	def createRetweet(status: TwitterStatus, retweetUser: String, retweetText: String) = {
		new TwitterStatus {
			val createdAt = status.createdAt
			val id = RETWEET_ID
			val text = "RT - " + retweetText
			val source = status.source
			val truncated = status.truncated
			val inReplyToStatusId = None
			val inReplyToUserId = None
			val favorited = status.favorited
			val user = new TwitterUser {
				val id = RETWEET_ID
				val name = retweetUser
				val screen_name = retweetUser
				val location = ""
				val followers_count = -1
				val statuses_count = -1
			}
		}
	}
}
