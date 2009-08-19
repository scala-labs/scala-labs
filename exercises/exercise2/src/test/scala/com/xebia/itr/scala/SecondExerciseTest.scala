package com.xebia.itr.scala

// root import because our own package is named "scala", which overrides the toplevel scala package
import _root_.scala.xml._

import org.joda.time._
import org.joda.time.format._

import org.scalatest._
import org.scalatest.junit.JUnit3Suite


/*
 * Exercise 2:
 *
 * This exercise will let you experiment with the Scala List class and its
 * many methods. As input we will use one or more instances of List[TwitterUser]
 *
 * Your assignment is to implement the methods of the TwitterUsers
 * object tested below. An empty implementation is available as a starting
 * point.
 */
class SecondExerciseTest extends JUnit3Suite {
    private def getFriends(): List[TwitterUser] = loadUsersFromXml("/friends.xml")
    private def getFollowers(): List[TwitterUser] = loadUsersFromXml("/followers.xml")

    private def loadUsersFromXml(xmlFileName: String): List[TwitterUser] = {
        val xml = XML.load(this.getClass.getResourceAsStream(xmlFileName))
        val friends = xml \\ "user"

        friends.elements.toList.map(s => TwitterUser(s))
    }


    // ========================================================================
    // The tests
    // ========================================================================

	def testFindPopularFriends() {
        // you are popular if you have at least 2000 followers
        expect(10) {
          TwitterUsers.thatArePopular(getFriends()).size
        }
    }

    def testFindScreenNamesOfPopularFriends() {
        // Imports can appear all over your code. This is a local import that also
        // includes an alias (sometimes handy to prevent name-clashes but used here
        // simply because we can).
       import scala.{TwitterUsers => Friends}

        //
        expect(List("headius", "twitterapi", "stephenfry", "macrumors", "spolsky", "martinfowler", "WardCunningham", "unclebobmartin", "pragdave", "KentBeck")) {
            Friends.thatArePopularByScreenName(getFriends)
        }
    }

    def testFindScreenNamesOfPupularFriendsSortedByPopularity() {
        expect(List("stephenfry", "macrumors", "twitterapi", "spolsky", "martinfowler", "KentBeck", "unclebobmartin", "pragdave", "WardCunningham", "headius")) {
            TwitterUsers.thatArePopularByScreenNameSortedbyPopularity(getFriends)
        }
    }

    def testFindPopularFriendsAndTheirRankings() {
        expect(
            List(("stephenfry",    714779),
                 ("macrumors",     74132),
                 ("twitterapi",    18817),
                 ("spolsky",       12607),
                 ("martinfowler",  8759),
                 ("KentBeck",      6440),
                 ("unclebobmartin",5175),
                 ("pragdave",      4462),
                 ("WardCunningham",4423),
                 ("headius",       2378))
        ) {
            TwitterUsers.thatArePopularByScreenNameAndPopularitySortedbyPopularity(getFriends)
        }
    }


        // Bonus: there are (at least) two ways to add these methods to the List itself. Implement them.

        // ==> implicit conversion
        // ==> mixin trait with self-type annotation

//        val coolFriends = new List[TwitterUser] with CoolTrait
//
//        coolFriends.cool

//    trait CoolTrait {
//        this: List[TwitterUser] =>
//
//        def cool() {
//            println("Cool !!!")
//        }
//    }

}