package com.xebia.snippet

import xml._
import _root_.net.liftweb._
import common._
import util.Helpers._
import http._

import auth.{AuthRole, userRoles}
import js.JsCmd
import net.liftweb.http.js.JsCmds._
import com.xebia.model._
import com.xebia.session._
import java.text.SimpleDateFormat
import _root_.java.util.Date
import com.xebia.utils.Utils._


class Account extends DispatchSnippet {

  var dispatch: DispatchIt = {
    case "signup" => (xhtml: NodeSeq) => signup(xhtml)
    case "edit" => (xhtml: NodeSeq) => edit(xhtml)
  }
  var firstname = ""
  var lastname = ""
  var email = ""
  var password = ""
  var birthdate: Date = _
  var fileHolder: Box[FileParamHolder] = Empty


  def signup(xhtml: NodeSeq): NodeSeq = {

    def createAccount() = {
      val u = User(firstname, lastname, email, password)
      if (!validateAllFields(u)) {
        S.error("There are validation errors")
        //Alert("Please fill in the remaining fields")
      } else {
        //hack to assign admin role to very first user
        if (User.findAll.isEmpty) {
          u.role = Role.findByName("Admin").get
        } else {
          u.role = Role.getDefaultRole
        }
        Model.persistAndFlush(u)
        UserHolder.setCurrentUser(u)
        userRoles(AuthRole(u.role.name))
        S.notice("Added user " + firstname + " with " + u.id);
        //SetHtml("actionmsg", <b>{"User created"}</b>) & RedirectTo("/index")
      }
    }

    //SHtml.ajaxForm(
    bind("acc", xhtml,
      "title" -> Text("Create Account"),
      "firstname" -> SHtml.text(firstname, firstname = _),
      "lastname" -> SHtml.text(lastname, lastname = _),
      "birthdate" -> SHtml.ajaxText("", parseAndValidateBirthdate _) % ("id" -> "birthdateinput"),
      "email" -> SHtml.ajaxText("", validateEmail _),
      "password" -> SHtml.password(password, password = _),
      "pictureupload" -> SHtml.fileUpload(fph => fileHolder = Full(fph)),
      "save" -> SHtml.submit("Create", createAccount)
    ) //)
  }

  def edit(xhtml: NodeSeq): NodeSeq = {
    def changeAccount() = {
      val u = User.findById(UserHolder.currentUser.id).get
      if (!validateAllExceptEmail(u)) {
        S.error("There are validation errors")
        //Alert("Please fill in the remaining fields")
      } else {
        u.firstName = firstname
        u.lastName = lastname
        u.password = password
        u.birthdate = birthdate
        Model.mergeAndFlush(u)
        UserHolder.setCurrentUser(u)
        S.notice(String.format("Changed user %s %s", u.firstName, u.lastName))
        //SetHtml("actionmsg", <b>{"Changes saved"}</b>)
      }
    }

    def userImage = {
      if (UserHolder.currentUser.photo != null) <img src="/service/userimage" height="130" width="100"/> else <span/>
    }

    bind("acc", xhtml,
      "title" -> Text("Edit Account"),
      "firstname" -> SHtml.text(UserHolder.currentUser.firstName, firstname = _),
      "lastname" -> SHtml.text(UserHolder.currentUser.lastName, lastname = _),
      "birthdate" -> SHtml.ajaxText(formatDate(UserHolder.currentUser.birthdate), parseAndValidateBirthdate _) % ("id" -> "birthdateinput"),
      "email" -> SHtml.text(UserHolder.currentUser.email, em => email = UserHolder.currentUser.email) % ("disabled" -> "disabled")
      ,
      "password" -> SHtml.password(UserHolder.currentUser.password, password = _),
      "userimage" -> userImage,
      "pictureupload" -> SHtml.fileUpload(fph => fileHolder = Full(fph)),
      "save" -> SHtml.submit("Change", changeAccount)
    )
  }


  //===========================
  //Validation
  //===========================

  def parseAndValidateBirthdate(date: String) {
    parseDate(date) match {
      case Full(d) => S.clearCurrentNotices;this.birthdate = d
      case _ => S.error("birthdate", "The date you entered is invalid")
    }
  }

  def validateEmail(email: String) = {
    this.email = email
    User.findByEmail(email) match {
      case Empty => S.clearCurrentNotices;
      Noop
      case _ => S.notice("email", "email address already exists. Choose another one");
      Noop
    }
  }

  def validateAllFields(u: User) = validateAllExceptEmail(u) && checkNotEmpty("email", email)

  def validateAllExceptEmail(u: User) = {
    checkNotEmpty("firstname", firstname) && checkNotEmpty("lastname", lastname) && checkNotEmpty("password", password) && validateUploadedFile(u: User)
  }


  def checkNotEmpty(name: String, value: String) = {
    val empty = value.isEmpty
    if (empty) S.notice(name, "Value requried")
    !empty
  }

  def validateUploadedFile(u: User) = {
    fileHolder match {
      case Full(FileParamHolder(_, null, _, _)) => println("1");
      true
      case Full(FileParamHolder(_, mime, _, data))
        if mime.startsWith("image/") => {
        u.photo = data
        u.mimeType = mime
        true
      }
      // If someone sends nothing...
      case Full(FileParamHolder(_, _, "", _)) => true
      case Full(something) => {
        S.error("pictureupload", "Invalid attachment. " + something + ". Use an image file")
        false
      }
      case _ => true
    }
  }

}