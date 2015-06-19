package org.scalalabs.intermediate.lab02

import scala.language.implicitConversions
object TwitterUsers {

  def thatArePopular(input: List[TwitterUser]): List[TwitterUser] = {
    input.filter(_.followersCount > 2000)
  }

  def thatArePopularByScreenName(input: List[TwitterUser]): List[String] = {
    thatArePopular(input).map(_.screen_name)
  }

  def thatArePopularByScreenNameSortedbyPopularity(input: List[TwitterUser]): List[String] = {
    thatArePopularByScreenName(input.sortWith(_.followersCount > _.followersCount))

    // alternative solutions:
    //        thatArePopularByScreenName(input.sortWith((first, second) => compareByFollowersCount(first, second)))
    //        thatArePopularByScreenName(input.sortWith(compareByFollowersCount(_, _)))
  }

  def thatArePopularByScreenNameAndPopularitySortedbyPopularity(input: List[TwitterUser]): List[(String, Int)] = {
    thatArePopular(input.sortWith(compareByFollowersCount(_, _))).map(friend ⇒ (friend.screen_name, friend.followersCount))
  }

  def thatAreInBothLists(firstList: List[TwitterUser], secondList: List[TwitterUser]): List[TwitterUser] = {
    firstList intersect secondList
  }

  private def compareByFollowersCount(firstUser: TwitterUser, secondUser: TwitterUser): Boolean = {
    firstUser.followersCount > secondUser.followersCount
  }
}

// ============================================================================
// Bonus implementation
// ============================================================================

object TwitterUsersBonus {
  implicit def listToTwitterUsers(users: List[TwitterUser]): TwitterUsersBonus = new TwitterUsersBonus(users)
}

class TwitterUsersBonus(val users: List[TwitterUser]) {
  def thatArePopular() = TwitterUsers.thatArePopular(users)
  def thatArePopularByScreenName(): List[String] = TwitterUsers.thatArePopularByScreenName(users)
  def thatArePopularByScreenNameSortedbyPopularity(): List[String] = TwitterUsers.thatArePopularByScreenNameSortedbyPopularity(users)
  def thatArePopularByScreenNameAndPopularitySortedbyPopularity = TwitterUsers.thatArePopularByScreenNameAndPopularitySortedbyPopularity(users)
  def thatAreAlsoIn(otherUsers: List[TwitterUser]) = TwitterUsers.thatAreInBothLists(users, otherUsers)
}
