package com.xebia.model

import _root_.scala.xml.XML

class TwitterTimeline(val statuses: List[TwitterStatus]) {

	def numberOfStatuses = statuses.length
}

object TwitterTimeline {

	def fromXML(node: scala.xml.Node) = {
		new TwitterTimeline((node \\ "status").elements.toList.map(s => TwitterStatus.fromXml(s)))
	}
	
}



