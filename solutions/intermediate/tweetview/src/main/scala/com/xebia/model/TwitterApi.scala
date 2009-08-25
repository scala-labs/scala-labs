package com.xebia.model
import com.xebia.config._

trait TwitterApi {

    def publicTimeline: TwitterTimeline;


    def userTimeline(user: TweetviewUser): TwitterTimeline;

    def friendsTimeline(user: TweetviewUser): TwitterTimeline;

    def updateStatus(user:TweetviewUser, t:String): Unit;
    
}
