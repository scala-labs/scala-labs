package com.xebia.itr.scala

// root import because our own package is named "scala", which overrides the toplevel scala package
import _root_.scala.xml._

import _root_.org.apache.commons.httpclient._
import _root_.org.apache.commons.httpclient.methods._
import _root_.org.apache.commons.httpclient.params.HttpMethodParams._
import _root_.org.apache.commons.httpclient.auth.AuthScope._
import org.apache.commons.httpclient.cookie.CookiePolicy._


/* Simple set of Twiter API URLs for easy reuse. */
object TwiterApiUrls {
    val publicTimelineUrl                   = "http://twitter.com/statuses/public_timeline.xml"
    val friendsTimelineUrl                  = "http://twitter.com/statuses/friends_timeline.xml"
    def userTimelineUrl(screenName: String) = "http://www.twitter.com/status/user_timeline/" + screenName + ".xml"
    def friendsUrl                          = "http://www.twitter.com/statuses/friends.xml"
}


object TwitterSession {
    def apply(): UnauthenticatedSession = {
        new UnauthenticatedSession()
    }

    def apply(user: String, password: String): AuthenticatedSession = {
        new AuthenticatedSession(user,password)
    }
}


/**
* The base class of both TwitterSession types
*/
abstract class TwitterSession {
    protected def httpGet(url: String): String
}


/*
 * Provides an interface to Twitter for all non-authorized calls.
 *
 * If you need access to the authenticated calls, see AuthenticatedSession.
 *
 * This class should be completely thread safe, allowing multiple simultaneous calls to Twitter via this object.
 *
 * All methods are fairly direct representations of calls specified in the
 * <a href="http://groups.google.com/group/twitter-development-talk/web/api-documentation">Twitter API Doc</a>
 */
class UnauthenticatedSession extends TwitterSession {
    import TwiterApiUrls._

    def publicTimeline(): TwitterTimeline = {
        mapToTimeline(getXml(publicTimelineUrl))
    }

    def userTimeline(screenName: String): TwitterTimeline = {
        mapToTimeline(getXml(userTimelineUrl(screenName)))
    }


    // ========================================================================
    // Implementation details
    // ========================================================================

    protected override def httpGet(url: String): String = {
        println("Unauthenticated get of " + url)

        val http = new HttpClient()
        val method = new GetMethod(url)

        method.getParams().setParameter(COOKIE_POLICY, IGNORE_COOKIES)

        http.executeMethod(method)

        new String(method.getResponseBody())
    }

    protected def getXml(url: String): Node = {
        XML.loadString(httpGet(url))
    }

    protected def mapToTimeline(xml: Node): TwitterTimeline = {
        new TwitterTimeline((xml \\ "status").elements.toList.map(s => TwitterStatus(s)))
    }
}



/*
 * Provides access to Twitter API methods that require authentication.
 *
 * Like UnauthenticatedSession, this class is thread safe, and more or less directly mirrors the
 * <a href="http://groups.google.com/group/twitter-development-talk/web/api-documentation">Twitter API Doc</a>
 */
class AuthenticatedSession(val userName: String, password: String) extends UnauthenticatedSession {
    import TwiterApiUrls._

    def friendsTimeline: TwitterTimeline = {
        mapToTimeline(getXml(friendsTimelineUrl))
    }

    def userTimeline: TwitterTimeline = {
        mapToTimeline(getXml(userTimelineUrl(userName)))
    }

    def friends: TwitterUsers = {
        mapToUsers(getXml(friendsUrl))
    }


    // ========================================================================
    // Implementation details
    // ========================================================================

    protected override def httpGet(url: String): String = {
        println("Authenticated get of " + url)

        val http = new HttpClient()
        val method = new GetMethod(url)

        http.getState().setCredentials(ANY, new UsernamePasswordCredentials(userName, password))

        method.setDoAuthentication(true)
        method.getParams().setParameter(COOKIE_POLICY, IGNORE_COOKIES)

        http.executeMethod(method)

        new String(method.getResponseBody())
    }

    protected def mapToUsers(xml: Node): TwitterUsers = {
        new TwitterUsers((xml \\ "user").elements.toList.map(s => TwitterUser(s)))
    }
}