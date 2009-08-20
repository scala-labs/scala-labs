package com.xebia.itr.scala

import org.scalatest._
import org.scalatest.junit.JUnit3Suite


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
 *
 * Hints:
 *
 * - All classes that implement the Iterable[T] trait can be treated as any
 *   other type of collection (i.e. they have methods like map, filter, etc.)
 *
 * Bonus:
 *
 * - implement tweeting (i.e. post tweets to twitter) and test that the tweet shows up in your timeline.
 *
 */
class ThirdExerciseTest extends JUnit3Suite {
    val testAccountUsername = "XebiaScalaItr"
    val testAccountPassword = "Scala!Is!Cool!"


    // ========================================================================
    // The tests
    // ========================================================================

    def testPublicTimelineWithoutAuthentication {
        val twitter:UnauthenticatedSession = TwitterSession()
        val publicTimeline:TwitterTimeline = twitter.publicTimeline

        expect(20) {publicTimeline.toList.size}
        expect(true) {publicTimeline.forall(_.user != null)}
    }

    def testFriendsTimelineWithAuthentication {
        val twitter:AuthenticatedSession = TwitterSession(testAccountUsername, testAccountPassword)
        val friendsTimeline = twitter.friendsTimeline

        expect(20) {friendsTimeline.toList.size}
        expect(true) {friendsTimeline.forall(_.user != null)}
    }

    def testFriendsTimelineShouldOnlyContainTweetsByFriends {
        val twitter:AuthenticatedSession = TwitterSession(testAccountUsername, testAccountPassword)

        val friendsTimeline = twitter.friendsTimeline
        val friends:TwitterUsers = twitter.friends

        expect(20) {friendsTimeline.toList.size}
        expect(true) {friendsTimeline.forall(tweet => friends.exists(_ == tweet.user))}
    }

    def testUserTimelineWithoutAuthentication {
        val twitter:UnauthenticatedSession = TwitterSession()
        val userTimeline:TwitterTimeline = twitter.userTimeline("sgrijpink")

        expect(true) {userTimeline.forall(_.user.screenName == "sgrijpink")}
    }

    def testUserTimelineWithAuthentication {
        val twitter:AuthenticatedSession = TwitterSession(testAccountUsername, testAccountPassword)
        val userTimeline:TwitterTimeline = twitter.userTimeline

        expect(true) {userTimeline.forall(_.user.screenName == testAccountUsername)}
    }

}