package com.xebia.itr.scala

import _root_.scala.xml._


abstract class TwitterUser {
    val id : Long
    val name : String
    val screen_name : String
    val description : String
    val location : String
    val url: String
    val profileImageUrl: String
    val friendsCount: Int
    val followersCount : Int
    val statusesCount : Int

    override def toString = name
    override def hashCode = id.hashCode

    override def equals(other: Any) = {
        // The usual if else would be perfectly ok...
        //
        // if (other.isInstanceOf[TwitterUser]){
        //     other.asInstanceOf[TwitterUser].id == id
        // } else {
        //     false
        // }

        // But doesn't this look a lot better ?
        other match {
            case otherUser: TwitterUser => id == otherUser.id
            case _ => false
        }
    }


}

object TwitterUser {
    def apply(node: Node): TwitterUser = {
        new TwitterUser {
            val id = (node \ "id").text.toLong
            val name = (node \ "name").text
            val screen_name = (node \ "screen_name").text
            val description = (node \ "description").text
            val location = (node \ "location").text
            val url = (node \ "url").text
            val profileImageUrl = (node \ "profile_image_url").text
            val friendsCount = (node \ "friends_count").text.toInt
            val followersCount = (node \ "followers_count").text.toInt
            val statusesCount = (node \ "statuses_count").text.toInt
        }
    }

}
