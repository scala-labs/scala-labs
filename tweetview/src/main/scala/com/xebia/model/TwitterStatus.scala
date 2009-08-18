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
  val retweetDetails : Option[TwitterRetweetDetails]
}


  /**
   * Object wrapper for transforming (format) into Status instances.
   */
  object TwitterStatus {
    def fromXml(node : scala.xml.Node) : TwitterStatus = {
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
        val retweetDetails = None
      }
    }
  }


