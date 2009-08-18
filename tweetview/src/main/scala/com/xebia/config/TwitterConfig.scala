/*
 * TwitterConfig.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.config

import com.xebia.model._

object MockTwitterConfig {
    lazy val twitter = new MockTwitter();
}


object OfflineTwitterConfig {
    lazy val twitter = new OfflineTwitter();
}


object OnlineTwitterConfig {
    lazy val twitter = new OnlineTwitter();
}

object TwitterClient {
    def client = {
        new TwitterClient(OfflineTwitterConfig)
    }
}

