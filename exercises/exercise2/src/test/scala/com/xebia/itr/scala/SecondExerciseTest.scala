package com.xebia.itr.scala

// root import because our own package is named "scala", which overrides the toplevel scala package
import _root_.scala.xml._

import org.joda.time._
import org.joda.time.format._

import org.scalatest._
import org.scalatest.junit.JUnit3Suite


/*
 * Exercise 1:
 *
 * Your job is to implement the Twiterstatus class (and it's associated classes) in
 * such a way that the tests in this suite all succeed.
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
    // This exercise will let you experiment with the Scala List class and its
    // many methods. As input we will use one or more instances of List[TwitterUser]
    //
    // Your assignment is to implement the methods of the TwitterUserListUtils
    // object tested below. An empty implementation is available as a starting
    // point.
    // ========================================================================

	def testFindAllPopularFriends() {
        // Imports can appear all over your code. This is a local import that also
        // includes an alias (sometimes handy to prevent name-clashes but used here
        // simply because we could).
        import scala.{TwitterUserListUtils => Friends}

        // you are popular if you have at least 2000 followers
        expect(17) {Friends.thatArePopular(getFriends()).size}

        // you are

        println("\n\nPopular friends: \n" +
                  getFriends().filter(_.followersCount > 2000).map(_.screen_name).mkString("\n"))

        println("\n\nPopular friends and their follower counts: \n" +
                  getFriends().filter(_.followersCount > 2000).map(friend => (friend.screen_name, friend.followersCount)).mkString("\n"))

        println("\n\nFriends and their follower counts (sorted by follower count): \n" +
                  getFriends().filter(_.followersCount > 2000).sort(_.followersCount > _.followersCount).map(friend => (friend.screen_name, friend.followersCount)).mkString("\n"))


        // Bonus: there are (at least) two ways to add these methods to the List itself. Implement them.

        // ==> implicit conversion
        // ==> mixin trait with self-type annotation

//        val coolFriends = new List[TwitterUser] with CoolTrait
//
//        coolFriends.cool
    }

//    trait CoolTrait {
//        this: List[TwitterUser] =>
//
//        def cool() {
//            println("Cool !!!")
//        }
//    }

}