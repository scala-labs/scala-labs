/*
 * RetweetFilter.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.xebia.model

/*
 * very basic filter that just filters out all statuses that contain the RT char sequence
*/
class RetweetFilter extends TweetFilter {

   override def filter(sts:Seq[TwitterStatus]):Seq[TwitterStatus] = {
       sts.filter(st => !st.text.contains("RT"))
   }

}
