package com.xebia.itr.scala


class TwitterUsers(val statuses: List[TwitterUser]) extends Iterable[TwitterUser] {
    def elements: Iterator[TwitterUser] = statuses.elements
}
