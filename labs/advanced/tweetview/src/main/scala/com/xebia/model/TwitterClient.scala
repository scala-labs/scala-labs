package com.xebia.model

class TwitterClient(env: {val twitter: TwitterApi}) {

    def publicTimeLine: TwitterTimeline = {
		env.twitter.publicTimeline
    }

    def userTimeLine(user:TweetviewUser): TwitterTimeline = {
        env.twitter.userTimeline(user)
    }

    def friendsTimeLine(user:TweetviewUser): TwitterTimeline = {
        env.twitter.friendsTimeline(user)
    }
}
