/*
 * TwitterApi.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

trait TwitterApi {

    def publicTimeline():Seq[TwitterStatus];

    def userTimeline(user:User):Seq[TwitterStatus];
}
