package org.scalalabs.intermediate.lab01

import scala.xml._

import java.util.Locale

import org.joda.time.format._

import org.scalatest.junit.JUnitSuite

import org.junit.Test

/*
 * Exercise 1:
 *
 * Your job is to implement the TwitterStatus class (and it's associated classes) in
 * such a way that the tests in this suite all succeed.
 * N.B.: a lot of code here is commented, just to let the lab compile. Uncomment all code, fix it by implementing the appropriate classes,
 * and make the tests pass.
 */
class FirstExerciseTest extends JUnitSuite {
  val twitterDateTimeFormat = DateTimeFormat.forPattern("EE MMM dd HH:mm:ss Z yyyy").withLocale(Locale.US)

  // TODO uncomment and implement TwitterStatus class
  // private def getListOfTweets(): List[TwitterStatus] = {
  //   val xml = XML.load(this.getClass.getResourceAsStream("/friends_timeline_agemooij.xml"))
  //   val statuses = xml \\ "status"
  // 
  //   // This is where the TwitterStatus domain class is instantiated with a scala.xml.Node
  //   // representing the <status> element.
  //   statuses.elements.toList.map(s => TwitterStatus(s))
  // }

  // ========================================================================
  // The tests
  // ========================================================================

  @Test
  def testTwitterStatusParsing() {
    fail("Fix this test")
    // TODO uncomment and fix test
    // val tweets = getListOfTweets()

    // there should be 20 tweets
    // expect(20) {tweets.size}
  }

  @Test
  def testAttributesOfFirstTweet() {
    fail("Fix this test")
    // TODO uncomment and fix test
    // val firstTweet = getListOfTweets()(0)
    // 
    // expect(3362029699L) {firstTweet.id}
    // 
    // expect(None) {firstTweet.inReplyToStatusId}
    // expect(None) {firstTweet.inReplyToUserId}
    // expect(false) {firstTweet.truncated}
    // expect (false) {firstTweet.favorited}
    // 
    // expect("Having much more fun working on #jaoo talks than  yesterday's hard drive crash redddddddcovery.") {
    //   firstTweet.text
    // }                                                    
    // 
    // expect(twitterDateTimeFormat.parseDateTime("Mon Aug 17 14:19:06 +0000 2009")) {
    //   firstTweet.createdAt
    // }
  }

  @Test
  def testAttributesOfUserAssociatedWithFirstTweet() {
    fail("uncomment and fix!")
    // TODO uncomment these tests and assertions
    // val firstTweetUser: TwitterUser = getListOfTweets()(0).user
    // 
    // expect(16665197L) {firstTweetUser.id}
    // expect("Martin Fowler") {firstTweetUser.name}
    // expect("martinfowler")   {firstTweetUser.screen_name}
    // expect("Loud Mouth, ThoughtWorks") {firstTweetUser.description}
    // expect("Boston") {firstTweetUser.location}
    // expect("http:// www.martinfowler.com/") {firstTweetUser.url}
    // expect("http:// a3.twimg.com/profile_images/79787739/mf-tg-sq_normal.jpg") {firstTweetUser.profileImageUrl}
    // expect(787) {firstTweetUser.statusesCount}
    // expect(166) {firstTweetUser.friendsCount}
    // expect(8735) {firstTweetUser.followersCount}
  }

}
