package bootstrap.liftweb

import net.liftweb.util._
import net.liftweb.http._
import net.liftweb.sitemap._
import net.liftweb.sitemap.Loc._
import net.liftweb.util.Helpers._
import net.liftweb.mapper.{DB, ConnectionManager, Schemifier, DefaultConnectionIdentifier, ConnectionIdentifier}
import java.sql.{Connection, DriverManager}
import javax.servlet.http.{HttpServletRequest}
import _root_.com.xebia.model._
import _root_.com.xebia.config._
import LoginState._



/**
  * A class that's instantiated early and run.  It allows the application
  * to modify lift's environment
  */
class Boot {
  def boot {

    // where to search snippet
    LiftRules.addToPackages("com.xebia")

    // Build SiteMap

    LiftRules.setSiteMap(SiteMap(MenuInfo.menu :_*))

    // map certain urls to the right place
    val rewriter: LiftRules.RewritePF = NamedPF("Twitter mapping") {
    case RewriteRequest(ParsePath("ctweeter" :: _, _, _,_), _, _) =>
       RewriteResponse("ctweeter" :: Nil, Map("ctweeter" -> "ctweeter"))
    }

    /*
     * Show the spinny image when an Ajax call starts
     */
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    /*
     * Make the spinny image go away when it ends
     */
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.early.append(makeUtf8)

    LiftSession.onBeginServicing = LoginState.onBeginServicing _  :: TwitterClient.onBeginServicing _ :: LiftSession.onBeginServicing
    
    
  }

  /**
   * Force the request to be UTF-8
   */
  private def makeUtf8(req: HttpServletRequest) {
    req.setCharacterEncoding("UTF-8")
  }

}

object MenuInfo {
  import Loc._

  def menu:List[Menu] =
   Menu(Loc("Home", List("index"), "Home")) ::
   Menu(Loc("Login", List("login"), "Login")) ::
   Menu(Loc("CometTweet", List("ctweet"), "CometTweet")) ::
   Menu(Loc("UserTwitterTimeline", List("mytimeline"), "My twitter time line")) :: Nil
}




