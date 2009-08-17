/*
 * TwitterClient.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

class TwitterClient(env: {val twitter:TwitterApi}) {

    def publicTimeLine():Seq[TwitterStatus] = {
       env.twitter.publicTimeline()
    }


    def userTimeLine():Seq[TwitterStatus] = {
        env.twitter.userTimeline()
    }
}
