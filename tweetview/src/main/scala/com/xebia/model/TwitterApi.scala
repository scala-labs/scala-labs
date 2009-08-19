/*
 * TwitterApi.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model
import com.xebia.config._

trait TwitterApi {

    def publicTimeline():Seq[TwitterStatus];

    def userTimeline(user:TweetviewUser):Seq[TwitterStatus];
}
