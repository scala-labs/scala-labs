package com.xebia.itr.scala

object TwitterUsers {

    def thatArePopular(input: List[TwitterUser]): List[TwitterUser] = {
        input.filter(_.followersCount > 2000)
    }

    def thatArePopularByScreenName(input: List[TwitterUser]): List[String] = {
        thatArePopular(input).map(_.screen_name)
    }

    def thatArePopularByScreenNameSortedbyPopularity(input: List[TwitterUser]): List[String] = {
        thatArePopularByScreenName(input.sort(_.followersCount > _.followersCount))
    }

    def thatArePopularByScreenNameAndPopularitySortedbyPopularity(input: List[TwitterUser]): List[(String, Int)] = {
        thatArePopular(input.sort(_.followersCount > _.followersCount)).map(friend => (friend.screen_name, friend.followersCount))
    }


}
