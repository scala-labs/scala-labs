package org.scalalabs.intermediate.lab02

import scala.xml._

import org.joda.time._
import org.joda.time.format._

import org.scalatest._
import org.scalatest.junit.JUnitSuite
import org.scalatest.junit.JUnitRunner

import org.junit.Test
import org.junit.runner.RunWith


/*
 * Exercise 2: Collect your bonus !
 *
 * This exercise is pretty much the same as before. All you need to do is to use an implicit
 * conversion to make the below tests compile. All the methods from before are now called as
 * if they were methods on the List class itself...
 */
@RunWith(classOf[JUnitRunner])
class SecondExerciseBonusTest extends JUnitSuite {
    private def getFriends(): List[TwitterUser] = loadUsersFromXml("/friends.xml")
    private def getFollowers(): List[TwitterUser] = loadUsersFromXml("/followers.xml")

    private def loadUsersFromXml(xmlFileName: String): List[TwitterUser] = {
        val xml = XML.load(this.getClass.getResourceAsStream(xmlFileName))
        val friends = xml \\ "user"

        friends.toList.map(s => TwitterUser(s))
    }


    // ========================================================================
    // The tests
    // ========================================================================

	@Test
	def testFindPopularFriends() {
        // TwitterUsers are popular if they have at least 2000 followers
    fail("TODO: uncomment and fix")
//        expect(10) {
//            getFriends.thatArePopular.size
//        }
    }

    @Test
	def testFindScreenNamesOfPopularFriends() {
      fail("TODO: uncomment and fix")
//        expect(List("headius", "twitterapi", "stephenfry", "macrumors", "spolsky", "martinfowler", "WardCunningham", "unclebobmartin", "pragdave", "KentBeck")) {
//            getFriends thatArePopularByScreenName
//        }
    }

    // the same List[String] as last time but now sorted by followersCount (highest first)
    @Test
	def testFindScreenNamesOfPupularFriendsSortedByPopularity() {
      fail("TODO: uncomment and fix")
//        expect(List("stephenfry", "macrumors", "twitterapi", "spolsky", "martinfowler", "KentBeck", "unclebobmartin", "pragdave", "WardCunningham", "headius")) {
//            getFriends thatArePopularByScreenNameSortedbyPopularity
//        }
    }

    // We expect a List[(String, Int)], i.e. a List of tuples, each with a screen name and a number of followers
    @Test
	def testFindPopularFriendsAndTheirRankings() {
      fail("TODO: uncomment and fix")
//        expect(
//            List(("stephenfry",    714779),
//                 ("macrumors",     74132),
//                 ("twitterapi",    18817),
//                 ("spolsky",       12607),
//                 ("martinfowler",  8759),
//                 ("KentBeck",      6440),
//                 ("unclebobmartin",5175),
//                 ("pragdave",      4462),
//                 ("WardCunningham",4423),
//                 ("headius",       2378))
//        ) {
//            getFriends thatArePopularByScreenNameAndPopularitySortedbyPopularity
//        }
    }

    // Hint: you might want to implement equals and hashcode for this one
    @Test
	def testFindFriendsThatAreAlsoFollowers() {
      fail("TODO: uncomment and fix")
//        expect(10) {
//            getFriends.thatAreAlsoIn(getFollowers).size
//        }
    }

}
