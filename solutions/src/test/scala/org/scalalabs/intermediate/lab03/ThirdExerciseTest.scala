package org.scalalabs.intermediate.lab03

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Ignore

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

  val testAuthInfo = new TwitterAuthInfo(
    oauthAccessToken = "66988471-6UejYlvm65JNG9DW5JRmpmTwE6X90Pyyzx3RbJEjf",
    oauthTokenSecret = "VMuNpQ7YZGCtoojtEBxoROj0bdEQFlzZrD6j6tbk")

  // ========================================================================
  // The tests
  // ========================================================================

  @Test
  @Ignore
  def testPublicTimelineWithoutAuthentication {
    val twitter: UnauthenticatedSession = TwitterSession()
    val publicTimeline: TwitterTimeline = twitter.publicTimeline

    assertResult(20) { publicTimeline.toList.size }
    assertResult(true) { publicTimeline.forall(_.user != null) }
  }

  @Test
  @Ignore
  def testFriendsTimelineWithAuthentication {
    val twitter: AuthenticatedSession = TwitterSession(testAuthInfo)
    val friendsTimeline = twitter.friendsTimeline

    assertResult(true) { friendsTimeline.forall(_.user != null) }
  }

  @Test
  @Ignore
  def testFriendsTimelineShouldOnlyContainTweetsByFriendsOrByMyself {
    val twitter: AuthenticatedSession = TwitterSession(testAuthInfo)

    val friendsTimeline = twitter.friendsTimeline
    val friends: TwitterUsers = twitter.friends

    assertResult(true) { friendsTimeline.forall(tweet => friends.exists(_ == tweet.user) || testAccountUsername == tweet.user.screenName) }
  }

  @Test
  @Ignore
  def testUserTimelineWithoutAuthentication {
    val twitter: UnauthenticatedSession = TwitterSession()
    val userTimeline: TwitterTimeline = twitter.userTimeline("sgrijpink")

    assertResult(true) { userTimeline.forall(_.user.screenName == "sgrijpink") }
  }

  @Test
  @Ignore
  def testUserTimelineWithAuthentication {
    val twitter: AuthenticatedSession = TwitterSession(testAuthInfo)
    val userTimeline: TwitterTimeline = twitter.userTimeline(testAccountUsername)

    assertResult(true) { userTimeline.forall(_.user.screenName == testAccountUsername) }
  }

  // Bonus exercise !!!

  @Test
  @Ignore
  def testTweet() {
    val twitter: AuthenticatedSession = TwitterSession(testAuthInfo)
    val baseText = "A test tweet from a scala-labs unit test. This test was run by "

    // this might a bit of a privacy-sensitive but I was looking for a way to be able to
    // recognize your own generated tweet from others. Other solutions that are less privacy
    // sensitive are more than welcome.

    val tweet = twitter.tweet(baseText + System.getProperty("user.name") + " on " + System.currentTimeMillis())
    assertResult(testAccountUsername) { tweet.user.screenName }
    assertResult(true) { tweet.text.startsWith(baseText) }
  }

}