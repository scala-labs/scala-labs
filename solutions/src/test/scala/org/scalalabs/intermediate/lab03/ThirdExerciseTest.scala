package org.scalalabs.intermediate.lab03

import scala.util.Random
import scala.xml._

import org.joda.time._
import org.joda.time.format._

import org.scalatest._
import org.scalatest.junit.JUnitSuite

import org.junit.Test


/*
 * Exercise 3: Talking http to the real deal: building a Twitter API
 *
 * This exercise will not really introduce you to all that many new features.
 * It simply makes you use everything you've learned already and apply it to
 * some API design.
 *
 * Your assignment is to implement the twitter API tested below on top of
 * HttpClient. The boring http requst stuff has already been done so you can
 * concentrate on the good stuff.
 *
 * Hints:
 *
 * - All classes that implement the Iterable[T] trait can be treated as any
 *   other type of collection (i.e. they have methods like map, filter, etc.)
 *
 * Bonus:
 *
 * - implement tweeting (i.e. post tweets to twitter). Posting a tweet returns
 *   the xml for the tweet you posted so a good API for tweet would be:
 *
 *   def tweet(text: String): TwitterStatus
 *
 *   The Twitter API docs for posting a status update are here:
 *
 *   http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses%C2%A0update
 *
 *   Note: Twitter will ignore duplicate tweets !!!
 *         Your tweets must be unique so use scala.util.Random !
 *
 */
class ThirdExerciseTest extends JUnitSuite {
    val testAccountUsername = "XebiaScalaItr"
    val testAccountPassword = "Scala!Is!Cool!"


    // ========================================================================
    // The tests
    // ========================================================================

    @Test
	def testPublicTimelineWithoutAuthentication {
        val twitter:UnauthenticatedSession = TwitterSession()
        val publicTimeline:TwitterTimeline = twitter.publicTimeline

        expect(20) {publicTimeline.toList.size}
        expect(true) {publicTimeline.forall(_.user != null)}
    }

    @Test
	def testFriendsTimelineWithAuthentication {
        val twitter:AuthenticatedSession = TwitterSession(testAccountUsername, testAccountPassword)
        val friendsTimeline = twitter.friendsTimeline

        expect(20) {friendsTimeline.toList.size}
        expect(true) {friendsTimeline.forall(_.user != null)}
    }

    @Test
	def testFriendsTimelineShouldOnlyContainTweetsByFriendsOrByMyself {
        val twitter:AuthenticatedSession = TwitterSession(testAccountUsername, testAccountPassword)

        val friendsTimeline = twitter.friendsTimeline
        val friends:TwitterUsers = twitter.friends

        expect(20) {friendsTimeline.toList.size}
        expect(true) {friendsTimeline.forall(tweet => friends.exists(_ == tweet.user) || testAccountUsername == tweet.user.screenName)}
    }

    @Test
	def testUserTimelineWithoutAuthentication {
        val twitter:UnauthenticatedSession = TwitterSession()
        val userTimeline:TwitterTimeline = twitter.userTimeline("sgrijpink")

        expect(true) {userTimeline.forall(_.user.screenName == "sgrijpink")}
    }

    @Test
	def testUserTimelineWithAuthentication {
        val twitter:AuthenticatedSession = TwitterSession(testAccountUsername, testAccountPassword)
        val userTimeline:TwitterTimeline = twitter.userTimeline

        expect(true) {userTimeline.forall(_.user.screenName == testAccountUsername)}
    }

    // Bonus exercise !!!

    @Test
	def testTweet() {
        val twitter:AuthenticatedSession = TwitterSession(testAccountUsername, testAccountPassword)
        val baseText = "A test tweet from a scala-labs unit test. This test was run by "

        val tweet = twitter.tweet(baseText + System.getProperty("user.name"));

        expect(testAccountUsername) {tweet.user.screenName}
        expect(true) {tweet.text.startsWith(baseText)}
    }

}