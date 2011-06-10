package org.scalalabs.intermediate.lab03

import scala.xml._


import oauth.signpost.commonshttp._
import org.apache.http.client.methods.{HttpPost, HttpGet}
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.{HttpRequest, HttpResponse}
import org.apache.http.params.CoreProtocolPNames

/*
* Scala-labs OAuth tokens to authenticate into Twitter.
* See <a href="http://dev.twitter.com/pages/oauth_faq">Twitter OAuth Docs</a> for details.
*/
class TwitterAuthInfo(
   val oauthAccessToken:String,
   val oauthTokenSecret:String
)

/* Simple set of Twiter API URLs for easy reuse. */
object TwiterApiUrls {
    val publicTimelineUrl                   = "http://api.twitter.com/1/statuses/public_timeline.xml"
    val friendsTimelineUrl                  = "http://api.twitter.com/1/statuses/friends_timeline.xml"
    def userTimelineUrl(screenName: String) = "http://api.twitter.com/1/statuses/user_timeline.xml?screen_name=" + screenName
    val friendsUrl                          = "http://api.twitter.com/1/statuses/friends.xml"
    val statusUpdateUrl                     = "http://api.twitter.com/1/statuses/update.xml"
}


object TwitterSession {
    def apply(): UnauthenticatedSession = {
        new UnauthenticatedSession()
    }

    def apply(authInfo: TwitterAuthInfo): AuthenticatedSession = {
        new AuthenticatedSession(authInfo)
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
 * <a href="http://dev.twitter.com/doc">Twitter API Doc</a>
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

        val http = new DefaultHttpClient()
        val method = new HttpGet(url)

        new BasicResponseHandler().handleResponse(http.execute(method))

    }

    protected def getXml(url: String): Node = {
        XML.loadString(httpGet(url))
    }

    protected def mapToTimeline(xml: Node): TwitterTimeline = {
        new TwitterTimeline((xml \\ "status").toList.map(s => TwitterStatus(s)))
    }
}

class OAuthService(authInfo: TwitterAuthInfo) {
  //scala-labs oauth credentials for twitter
  private val consumerKey = "TprkmO1olOHKn9rbth5o6Q"
  private val consumerSecret = "6EOJJHhb9ooo0zRiJoK87PbwnVKoUpwDZSCi7Lct1DU"

  def sign(request: HttpRequest) = {
    val consumer =
      new CommonsHttpOAuthConsumer(consumerKey, consumerSecret)
    consumer.setTokenWithSecret(authInfo.oauthAccessToken, authInfo.oauthTokenSecret)
    consumer.sign(request)
  }

}


/*
 * Provides access to Twitter API methods that require authentication.
 *
 * Like UnauthenticatedSession, this class is thread safe, and more or less directly mirrors the
 * <a href="http://dev.twitter.com/doc">Twitter API Doc</a>
 */
class AuthenticatedSession(val authInfo: TwitterAuthInfo) extends UnauthenticatedSession {
    import TwiterApiUrls._

    def friendsTimeline: TwitterTimeline = {
        mapToTimeline(getXml(friendsTimelineUrl))
    }

    def friends: TwitterUsers = {
        mapToUsers(getXml(friendsUrl))
    }

    def tweet(text: String): TwitterStatus = {
        val response = httpPost(statusUpdateUrl, Map("status" -> text))

        println("response = " + response)

        TwitterStatus(XML.loadString(response))
    }


    // ========================================================================
    // Implementation details
    // ========================================================================

    protected override def httpGet(url: String): String = {
      println("Authenticated get of " + url)

      val http = new DefaultHttpClient()
      val get = new HttpGet(url)

      new OAuthService(authInfo).sign(get);

      new BasicResponseHandler().handleResponse(http.execute(get))
    }


    def httpPost(url: String, parameters: Map[String, String]): String = {
      import collection.JavaConversions._
      val http = new DefaultHttpClient()
      val post = new HttpPost(url)

      val postParams:List[BasicNameValuePair] =
        for((key,value) <- parameters.toList) yield new BasicNameValuePair(key, value)
      post.setEntity(new UrlEncodedFormEntity(seqAsJavaList(postParams), "UTF-8"))

      post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

      new OAuthService(authInfo).sign(post);

      val response: HttpResponse = http.execute(post)
      new BasicResponseHandler().handleResponse(response)
    }

    protected def mapToUsers(xml: Node): TwitterUsers = {
        new TwitterUsers((xml \\ "user").toList.map(s => TwitterUser(s)))
    }
}
