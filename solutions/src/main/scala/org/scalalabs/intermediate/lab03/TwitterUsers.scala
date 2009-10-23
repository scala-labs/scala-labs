package org.scalalabs.intermediate.lab03


class TwitterUsers(val statuses: List[TwitterUser]) extends Iterable[TwitterUser] {
    def elements: Iterator[TwitterUser] = statuses.elements
}
