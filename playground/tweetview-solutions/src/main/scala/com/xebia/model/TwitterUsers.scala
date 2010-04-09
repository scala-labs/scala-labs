package com.xebia.model




class TwitterUsers(val statuses: List[TwitterUser]) extends Iterable[TwitterUser] {
    def elements: Iterator[TwitterUser] = statuses.elements
}