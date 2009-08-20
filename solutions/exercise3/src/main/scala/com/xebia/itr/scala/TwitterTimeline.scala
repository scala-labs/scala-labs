package com.xebia.itr.scala


class TwitterTimeline(val statuses: List[TwitterStatus]) extends Iterable[TwitterStatus] {
    def elements: Iterator[TwitterStatus] = statuses.elements
}
