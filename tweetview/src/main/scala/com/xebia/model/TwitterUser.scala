/*
 * TwitterUser.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

abstract class TwitterUser {
  val id : Long
  val name : String
  val screen_name : String
  val location : String
  val followers_count : Int
  val statuses_count : Int
}


  /**
   * Object wrapper for transforming (format) into Status instances.
   */
  object TwitterUser {
    def fromXml(node : scala.xml.Node) : TwitterUser =
    {
      new TwitterUser {        
        val id = (node \ "id").text.toLong
        val name = (node \ "name").text
        val screen_name = (node \ "screen_name").text
        val location = (node \ "location").text
        val followers_count = (node \ "followers_count").text.toInt
        val statuses_count = (node \ "statuses_count").text.toInt
      }
    }
  }
