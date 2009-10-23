package org.scalalabs.intermediate.lab03


class TwitterTimeline(val statuses: List[TwitterStatus]) extends Iterable[TwitterStatus] {
    def elements: Iterator[TwitterStatus] = statuses.elements
}
