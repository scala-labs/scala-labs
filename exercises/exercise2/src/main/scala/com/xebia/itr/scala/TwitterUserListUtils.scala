package com.xebia.itr.scala

object TwitterUserListUtils {

    def thatArePopular(input: List[TwitterUser]): List[TwitterUser] = {
        input.filter(_.followersCount > 800)
    }

}
