/*
 * MockTwitter.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

class MockTwitter extends TwitterApi {

    def publicTimeline():Seq[TwitterStatus] = {
      createStatus(1L, 1L, "piet")::createStatus(2L, 2L, "klaas")::Nil
    }

    def userTimeline(user:User):Seq[TwitterStatus] = {
        Nil
    }


    def createStatus(statusId:Long, userId:Long, userName:String):TwitterStatus = {
        val status:TwitterStatus = new TwitterStatus {
        val createdAt = "20090601"
        val id = statusId
        val text = "mockText"
        val source = "http://apiwiki.twitter.com/"
        val truncated = false
        val inReplyToStatusId = None
        val inReplyToUserId = Some(2L)
        val favorited = false
        val retweetDetails = None
        val user = new TwitterUser {
            val id = userId
            val name = userName
            val screen_name = userName
            val location = "Netherlands"
            val followers_count = 3
            val statuses_count = 2
        }     
     }
     status
    }
}
