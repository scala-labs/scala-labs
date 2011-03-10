package com.xebia.snippet

import com.xebia.model._
import xml.NodeSeq
import net.liftweb.common._
import net.liftweb.util.Helpers._
import scala.xml.{NodeSeq, Text}
import net.liftweb.http.js._
import JsCmds._
import net.liftweb.http.{S, SHtml, DispatchSnippet}
import net.liftweb.http.auth._

class Users extends DispatchSnippet {

  def dispatch: DispatchIt = {
    case "show" => show _
  }

  def show(xhtml: NodeSeq): NodeSeq = {

    def userTable: NodeSeq = buildUserTable(User.findAll, xhtml)

    def updateTable(): JsCmd = {
      JsCmds.SetHtml("user_table", userTable)
    }

    def removeUser(id: Long): JsCmd = {
      User.removeById(id)
      updateTable
    }

    def updateRole(userId: Long)(roleName: String): JsCmd = {
      Role.findByName(roleName) match {
        case Full(role) => {
          User.findById(userId) match {
            case Some(user) => {
              user.role = role
              User.persist(user);
              userRoles.remove
              userRoles(AuthRole(user.role.name))
              S.notice("User " + user.id + " saved")
              };
            case None => S.error("user with id " + userId + " not found")
          }
        }
        case _ => S.error("role with name " + roleName + " not found")
      }
      updateTable
    }


    def buildUserTable(users: List[User], xhtml: NodeSeq) = {
      users.flatMap(user =>
        bind("entry", chooseTemplate("users", "tableEntry", xhtml),
          "firstname" -> Text(user.firstName),
          "lastname" -> Text(user.lastName),
          "email" -> Text(user.email),
          "role" -> SHtml.ajaxSelect(roleChoices, if(user.role != null) Full(user.role.name) else Empty, updateRole(user.id) _),
          "remove" -> SHtml.ajaxButton("remove", () => Confirm("Do you really want to delete this user", removeUser(user.id)))
        )
      )
    }


    bind("users", xhtml, "table" -> userTable)
  }


  private def roleChoices(): List[(String, String)] = Role.findAll.map(role => (role.name -> role.name))

}