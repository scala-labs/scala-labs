package org.scalalabs.intermediate.lab03

import scala.xml._

import org.apache.commons.httpclient._
import org.apache.commons.httpclient.methods._
import org.apache.commons.httpclient.params.HttpMethodParams._
import org.apache.commons.httpclient.auth.AuthScope._
import org.apache.commons.httpclient.cookie.CookiePolicy._


/* Simple set of Twiter API URLs for easy reuse. */
object TwiterApiUrls {
    val publicTimelineUrl                   = "http://twitter.com/statuses/public_timeline.xml"
    val friendsTimelineUrl                  = "http://twitter.com/statuses/friends_timeline.xml"
    def userTimelineUrl(screenName: String) = "http://www.twitter.com/status/user_timeline/" + screenName + ".xml"
    val friendsUrl                          = "http://www.twitter.com/statuses/friends.xml"
}


object TwitterSession {

}


/**
* The base class of both TwitterSession types
*/
abstract class TwitterSession {
    // an abstract method
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
}



/*
 * Provides access to Twitter API methods that require authentication.
 *
 * Like UnauthenticatedSession, this class is thread safe, and more or less directly mirrors the
 * <a href="http://groups.google.com/group/twitter-development-talk/web/api-documentation">Twitter API Doc</a>
 */
class AuthenticatedSession(val userName: String, password: String) extends UnauthenticatedSession {



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
}