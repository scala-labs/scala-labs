package com.xebia.model

import scala.util.matching.Regex

import scala.collection.mutable.Set

object RetweetFilter extends TweetFilter {

	def filter(timeline: TwitterTimeline): TwitterTimeline = timeline
    
}
