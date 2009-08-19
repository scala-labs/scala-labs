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

        // alternative solutions:
//        thatArePopularByScreenName(input.sort((first, second) => compareByFollowersCount(first, second)))
//        thatArePopularByScreenName(input.sort(compareByFollowersCount(_, _)))
    }

    def thatArePopularByScreenNameAndPopularitySortedbyPopularity(input: List[TwitterUser]): List[(String, Int)] = {
        thatArePopular(input.sort(compareByFollowersCount(_, _))).map(friend => (friend.screen_name, friend.followersCount))
    }

    def thatAreInBothLists(firstList: List[TwitterUser], secondList: List[TwitterUser]): List[TwitterUser] = {
        firstList intersect secondList
    }


    private def compareByFollowersCount(firstUser: TwitterUser, secondUser: TwitterUser): Boolean = {
       firstUser.followersCount > secondUser.followersCount
    }
}
