package com.xebia.snippet

import _root_.scala.xml._
import _root_.net.liftweb.http._
import _root_.net.liftweb.util._
import S._
import SHtml._
import Helpers._

import _root_.com.xebia.model._
import _root_.com.xebia.config._

import java.util.Date

class TwitterTimeline {
    val formatter = new java.text.SimpleDateFormat("yyyy/MM/dd")

    def showPublic (xhtml : NodeSeq) : NodeSeq = {
        bindEntries(xhtml, filter(TwitterClient.client.publicTimeLine).statuses)
    }

    def showUser (xhtml : NodeSeq) : NodeSeq = {
        LoginState.currentUser.map(u => bindEntries(xhtml, filter(TwitterClient.client.userTimeLine(u)).statuses)).openOr(Nil)
    }

    def showFriends (xhtml : NodeSeq) : NodeSeq = {
        LoginState.currentUser.map(u => bindEntries(xhtml, TwitterClient.client.friendsTimeLine(u).statuses)).openOr(Nil)
    }

    def bindEntries(xhtml : NodeSeq, statusSeq:Seq[TwitterStatus]) : NodeSeq = {
        val entries = statusSeq match {
            case Nil => Text("No public timeline found")
            case tlines => tlines.flatMap({tline =>
                        bind("st", chooseTemplate("status", "entry", xhtml),
                             "vcard"      -> vcard(tline.user),
                             "createdAt" -> Text(tline.createdAt),
                             "text"      -> Text(tline.text),
                             "userName"  -> Text(tline.user.name))
                    })
        }
        bind("status", xhtml, "entry" -> entries)
    }

    def vcard(u:TwitterUser) = {
        <span class="thumb vcard author">
            <a class="url" href={u.url}>
                <img height="48" width="48" src={u.profileImage} class="photo fn" alt={u.name}/>
            </a>
        </span>
    }

    private def filter(timeline: com.xebia.model.TwitterTimeline): com.xebia.model.TwitterTimeline = {
        S.param("filter") match {
            // ADD YOUR OWN FILTERS HERE
            // The HTTP param contains the filter name, in this case "retweet_filter"
            case Full("retweet_filter") => RetweetFilter.filter(timeline)
            case _ => timeline
        }
    }
}



