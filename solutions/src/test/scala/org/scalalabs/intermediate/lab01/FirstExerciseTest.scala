package org.scalalabs.intermediate.lab01

import scala.xml._

import java.util.Locale

import org.joda.time.format._

import org.scalatest.junit.JUnitSuite

import org.junit.Test

/*
 * Exercise 1:
 *
 * Your job is to implement the TwiterStatus class (and it's associated classes) in
 * such a way that the tests in this suite all succeed.
 */
class FirstExerciseTest extends JUnitSuite {
  val twitterDateTimeFormat = DateTimeFormat.forPattern("EE MMM dd HH:mm:ss Z yyyy").withLocale(Locale.US)

  private def getListOfTweets(): List[TwitterStatus] = {
    val xml = XML.load(this.getClass.getResourceAsStream("/friends_timeline.xml"))
    val statuses = xml \\ "status"

    // This is where the TwitterStatus domain class is instantiated with a scala.xml.Node
    // representing the <status> element.
    statuses.toList.map(s => TwitterStatus(s))
  }

  // ========================================================================
  // The tests
  // ========================================================================

  @Test
  def testTwitterStatusParsing() {
    val tweets = getListOfTweets()

    // there should be 20 tweets
    assertResult(20) { tweets.size }
  }

  @Test
  def testAttributesOfFirstTweet() {
    val firstTweet = getListOfTweets()(0)

    assertResult(3362029699L) { firstTweet.id }

    assertResult(None) { firstTweet.inReplyToStatusId }
    assertResult(None) { firstTweet.inReplyToUserId }
    assertResult(false) { firstTweet.truncated }
    assertResult(false) { firstTweet.favorited }

    assertResult("Having much more fun working on #jaoo talks than  yesterday's hard drive crash recovery.") {
      firstTweet.text
    }

    assertResult(twitterDateTimeFormat.parseDateTime("Mon Aug 17 14:19:06 +0000 2009")) {
      firstTweet.createdAt
    }
  }

  @Test
  def testAttributesOfUserAssociatedWithFirstTweet() {
    val firstTweetUser: TwitterUser = getListOfTweets()(0).user

    assertResult(16665197L) { firstTweetUser.id }
    assertResult("Martin Fowler") { firstTweetUser.name }
    assertResult("martinfowler") { firstTweetUser.screen_name }
    assertResult("Loud Mouth, ThoughtWorks") { firstTweetUser.description }
    assertResult("Boston") { firstTweetUser.location }
    assertResult("http://www.martinfowler.com/") { firstTweetUser.url }
    assertResult("http://a3.twimg.com/profile_images/79787739/mf-tg-sq_normal.jpg") { firstTweetUser.profileImageUrl }
    assertResult(787) { firstTweetUser.statusesCount }
    assertResult(166) { firstTweetUser.friendsCount }
    assertResult(8735) { firstTweetUser.followersCount }
  }

}