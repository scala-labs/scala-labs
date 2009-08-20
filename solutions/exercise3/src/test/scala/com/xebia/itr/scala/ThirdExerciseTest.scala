package com.xebia.itr.scala

import org.scalatest._
import org.scalatest.junit.JUnit3Suite


/*
 * Exercise 3: Talking http to the real deal
 *
 * This exercise will
 *
 * Your assignment is to
 */
class ThirdExerciseTest extends JUnit3Suite {
    val testAccountUsername = "XebiaScalaItr"
    val testAccountPassword = "Scala!Is!Cool!"


    // ========================================================================
    // The tests
    // ========================================================================

    def testPublicTimelineWithoutAuthentication() {
        val twitter = TwitterSession()

        println("\n\nPublic timeline: \n" + twitter.publicTimeline().mkString("\n"))
    }

    def testFriendsTimelineWithAuthentication() {
        val twitter = TwitterSession(testAccountUsername, testAccountPassword)

        println("\n\nFriends timeline: \n" + twitter.friendsTimeline().mkString("\n"))
    }

}