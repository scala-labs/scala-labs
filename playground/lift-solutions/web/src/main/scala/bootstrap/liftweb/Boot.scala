/*
 * Copyright 2008 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package bootstrap.liftweb

import _root_.java.util.Locale

import _root_.net.liftweb.util.LogBoot
import _root_.net.liftweb.http._
import _root_.net.liftweb.http.auth._
import _root_.net.liftweb.http.provider._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import auth.{userRoles, AuthRole}
import com.xebia.rest._
import S.?
import com.xebia.model.{User, Role=>RoleEntity}
import com.xebia.session._
import net.liftweb.common.{Empty, Box, Full}
import net.liftweb.widgets.autocomplete.AutoComplete

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    LogBoot.defaultProps =
      """<?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
      <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
      <appender name="appender" class="org.apache.log4j.ConsoleAppender">
      <layout class="org.apache.log4j.SimpleLayout"/>
      </appender>
      <root>
      <priority value ="DEBUG"/>
      <appender-ref ref="appender"/>
      </root>
<logger name="o.h" additivity="false">
    <level value="warn"/>
  </logger>
<logger name="org.hibernate" additivity="false">
    <level value="warn"/>
  </logger>

<logger name="org.hibernate.sql" additivity="false">
    <level value="debug"/>
  </logger>


      </log4j:configuration>
      """

    // where to search snippet
    LiftRules.addToPackages("com.xebia")
    LiftRules.dispatch.prepend(DispatchRestAPI.dispatch)
    //LiftRules.dispatch.append()

    //===========================
    //Authentication
    //===========================
    val adminRole = "Admin"
    val userRole = "User"
    val roles = AuthRole(adminRole, AuthRole(userRole))
    RoleEntity.initRoles(adminRole, userRole)

/*


   def initRoles(roles: String*) = {
      import com.xebia.model._
      val sem = Model.newEM
      def findAllRoles() = {
        sem.createQuery[Role]("Select r from Role r").findAll.toList
      }
      val persistedRoles = findAllRoles.map(r => r.name)
      for (role <- roles) {
        if (!persistedRoles.contains(role)) {
          sem.persist(Role(role));
        }
      }
      sem.flush
      println("persisted   " + findAllRoles)
      sem.close
    }
*/

    LiftRules.httpAuthProtectedResource.append {
      //      case (ParsePath("pages" :: "account" :: _, _, _, _)) =>
      //        roles.getRoleByName("Admin")
      //case Req("pagesold" :: _, _, GetRequest) => Empty; //roles.getRoleByName("Admin");//Empty

      //case Req("userimage" :: _, _, GetRequest) => Empty;//roles.getRoleByName("User"); //Empty
      case Req("api" :: "position" :: _, _, GetRequest) => roles.getRoleByName("User"); //Empty

    }

    LiftRules.authentication = HttpBasicAuthentication("Tracker") {
      case (userEmail, userPass, _) => {
        println("Authenticating: " + userEmail)
        import com.xebia.model._

        User.findByEmail(userEmail) match {
          case Full(user) if (user.password == userPass) => {
            println("Auth succeeded for " + userEmail)
            UserHolder.setCurrentUser(user)
            userRoles(AuthRole(user.role.name))
            true
          }
          case Full(user) => {
            println("Auth failed for " + userEmail)
            false
          }
          case _ => {
            println("User unknown " + userEmail)
            false
          }
        }
      }
    }


    // Define a simple test clause that we can use for multiple menu items
    val IfLoggedIn = If(() => UserHolder.loggedIn, () => RedirectResponse("/index"))
    val NotLoggedIn = If(() => !UserHolder.loggedIn, () => RedirectResponse("/index"))
    def mustHaveRole(role:String) = If(() => (UserHolder.loggedIn && (UserHolder.currentUser.role.name == role)), () => RedirectResponse("/index"))
//    def mustHaveRole(role:String) = If(() => (UserHolder.loggedIn && (userRoles.exists(_.name == role)), () => RedirectResponse("/index")))
    val LogoutAction = EarlyResponse(() => {UserHolder.logout; S.request.foreach(_.request.session.terminate); S.redirectTo(S.referer openOr "/")})

    // Set up a site map
    val entries = SiteMap(Menu(Loc("Home", "index" :: Nil, ?("Home"))),
      //Menu(Loc("Add Author", "pagesold" :: "authors" :: "add" :: Nil, ?("Add Author"), List(Hidden, IfLoggedIn))),


      Menu(Loc("service", "service" :: "" :: Nil, ?("service"), List(Hidden, IfLoggedIn))),
      Menu(Loc("Connect", "pages" :: "location" :: "connect" :: Nil, ?("Connect"), IfLoggedIn)),
      Menu(Loc("Spy", "pages" :: "location" :: "map" :: Nil, ?("Spy"), IfLoggedIn)),
      Menu(Loc("Useradmin", "pages" :: "admin" :: "users" :: Nil, ?("Useradmin"), mustHaveRole(adminRole))),
      Menu(Loc("Edit Account", "pages" :: "account" :: "edit" :: Nil, ?("Edit Account"), IfLoggedIn)),
      Menu(Loc("Login", "pages" :: "account" :: "login" :: Nil, ?("Login"), NotLoggedIn)),
      Menu(Loc("Sign Up", "pages" :: "account" :: "add" :: Nil, ?("Sign Up"), NotLoggedIn)),
      Menu(Loc("Logout", List("logout"), S.?("Logout"), List(IfLoggedIn, LogoutAction))))

    LiftRules.setSiteMap(entries)



    //================
    // Widgets
    //================
    AutoComplete.init


    // And now, for a little fun :)
    val swedishChef = new Locale("chef")

    LiftRules.useXhtmlMimeType = false

    object swedishOn extends SessionVar(false)

    def localeCalculator(request: Box[HTTPRequest]): Locale =
      request.flatMap(_.param("swedish") match {
        case Nil if swedishOn.is == true => Full(swedishChef)
        case Nil => Full(LiftRules.defaultLocaleCalculator(request))
        case "true" :: _ => {
          swedishOn(true);
          Full(swedishChef)
        }
        case "false" :: _ => {
          swedishOn(false);
          Full(LiftRules.defaultLocaleCalculator(request))
        }
      }).openOr(Locale.getDefault())

    LiftRules.localeCalculator = localeCalculator _
  }

}

