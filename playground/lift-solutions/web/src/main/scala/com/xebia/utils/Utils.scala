package com.xebia.utils

import java.text.SimpleDateFormat
import _root_.java.util.Date
import net.liftweb.common._
import xml.Node
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds._

object Utils {

  //=============
  // Script
  //=============
  def onLoadScript(cmd: JsCmd): Node = Script(OnLoad(cmd))

  //=============
  // Date utils
  //=============
  val dateFormat = "dd/MM/yyyy"
  val dateTimeFormat = dateFormat + " hh:mm:ss"

  def parseDate(date: String): Box[Date] = {
    try {
      Full(new SimpleDateFormat(dateFormat).parse(date))
    } catch {
      case e => Failure(e.getMessage)
    }
  }

  def formatDate(date: Date) = format(date, dateFormat)

  def formatDateTime(date: Date) = format(date, dateTimeFormat)

  private def format(date: Date, format: String) = if (date != null) new SimpleDateFormat(format).format(date) else ""

}