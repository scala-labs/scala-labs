/*
 * TwitterStatus.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

abstract class TwitterStatus {
	val createdAt : String
	val id : Long
	val text : String
	val source : String
	val truncated : Boolean
	val inReplyToStatusId : Option[Long]
	val inReplyToUserId : Option[Long]
	val favorited : Boolean
	val user : TwitterUser

	def isRetweet = 999L == id

	override def toString = {
		val sb = new StringBuilder("TwitterStatus(")
		sb.append("createdAt: ").append(createdAt).append(", ")
		sb.append("id: ").append(id).append(", ")
		sb.append("text: ").append(text).append(", ")
		sb.append("source: ").append(source).append(", ")
		sb.append("truncated: ").append(truncated).append(", ")
		sb.append("inReplyToStatusId: ").append(inReplyToStatusId).append(", ")
		sb.append("inReplyToUserId: ").append(inReplyToUserId).append(", ")
		sb.append("favorited: ").append(favorited).append(", ")
		sb.append("user: ").append(user).append(")")
		sb.toString
	}
}


/**
 * Object wrapper for transforming (format) into Status instances.
 */
object TwitterStatus {

	val RETWEET_ID = 999L


    def fromXml(node : scala.xml.Node): TwitterStatus = {
		new TwitterStatus {
			val createdAt = (node \ "created_at").text
			val id = (node \ "id").text.toLong
			val text = (node \ "text").text
			val source = (node \ "source").text
			val truncated = (node \ "truncated").text.toBoolean
			val inReplyToStatusId =
				if ((node \ "in_reply_to_status_id").text != "")
					Some((node \"in_reply_to_status_id").text.toLong)
				else
					None
			val inReplyToUserId =
				if ((node \ "in_reply_to_user_id").text != "")
					Some((node \"in_reply_to_user_id").text.toLong)
				else
					None
			val favorited = (node \ "favorited").text.toBoolean
			val user = TwitterUser.fromXml((node \ "user")(0))
		}
    }

	def fromRetweet(status: TwitterStatus, retweetUser: String, retweetText: String) = {
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


