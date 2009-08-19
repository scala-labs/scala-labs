package com.xebia.model

trait TwitterApi {

    def publicTimeline: TwitterTimeline;

    def userTimeline(user: User): TwitterTimeline;
}
