/*
 * TweetFilter.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

trait TweetFilter {

    def filter(sts:Seq[TwitterStatus]):Seq[TwitterStatus]
}
