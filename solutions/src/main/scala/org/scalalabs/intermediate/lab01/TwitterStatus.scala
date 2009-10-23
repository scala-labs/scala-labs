package org.scalalabs.intermediate.lab01

import scala.xml._

import org.joda.time._
import org.joda.time.format._


abstract class TwitterStatus {
    val id: Long
    val text: String
    val user: TwitterUser
    val createdAt: DateTime
    val source: String
    val truncated: Boolean
    val inReplyToStatusId: Option[Long]
    val inReplyToUserId: Option[Long]
    val favorited: Boolean
}

object TwitterStatus {
    val fmt = DateTimeFormat.forPattern("EE MMM dd HH:mm:ss Z yyyy")

    def apply(node: Node): TwitterStatus = {
      new TwitterStatus {
        val id = (node \ "id").text.toLong
        val text = (node \ "text").text
        val user = TwitterUser((node \ "user")(0))
        val source = (node \ "source").text
        val createdAt = fmt.parseDateTime((node \ "created_at").text)
        val truncated = (node \ "truncated").text.toBoolean
        val favorited = (node \ "favorited").text.toBoolean

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
      }
    }
}
