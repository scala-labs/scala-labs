package com.xebia.model

trait TweetFilter {

    def filter(statuses: TwitterTimeline): TwitterTimeline

}
