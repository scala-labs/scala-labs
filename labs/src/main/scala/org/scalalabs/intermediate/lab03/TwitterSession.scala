package org.scalalabs.intermediate.lab03

import org.apache.http.HttpRequest
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{ BasicResponseHandler, DefaultHttpClient }

/*
* Scala-labs OAuth tokens to authenticate into Twitter.
* See <a href="http://dev.twitter.com/pages/oauth_faq">Twitter OAuth Docs</a> for details.
*/
class TwitterAuthInfo(
  val oauthAccessToken: String,
  val oauthTokenSecret: String)

/* Simple set of Twiter API URLs for easy reuse. */
object TwiterApiUrls {
  val publicTimelineUrl = "http://api.twitter.com/1/statuses/public_timeline.xml"
  val friendsTimelineUrl = "http://api.twitter.com/1/statuses/friends_timeline.xml"
  def userTimelineUrl(screenName: String) = "http://api.twitter.com/1/statuses/user_timeline.xml?screen_name=" + screenName
  val friendsUrl = "http://api.twitter.com/1/statuses/friends.xml"
  val statusUpdateUrl = "http://api.twitter.com/1/statuses/update.xml"
}

object TwitterSession {

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
 * <a href="http://dev.twitter.com/doc">Twitter API Doc</a>
 */
class UnauthenticatedSession extends TwitterSession {

  // ========================================================================
  // Implementation details
  // ========================================================================

  protected override def httpGet(url: String): String = {
    println("Unauthenticated get of " + url)

    val http = new DefaultHttpClient()
    val method = new HttpGet(url)

    new BasicResponseHandler().handleResponse(http.execute(method))
  }
}

/*
 * Provides access to Twitter API methods that require authentication.
 *
 * Like UnauthenticatedSession, this class is thread safe, and more or less directly mirrors the
 * <a href="http://dev.twitter.com/doc">Twitter API Doc</a>
 */
class AuthenticatedSession(val authInfo: TwitterAuthInfo) extends UnauthenticatedSession {

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

  // def httpPost(url: String, parameters: Map[String, String]): String = {
  //
  //   post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
  //
  // }
}
