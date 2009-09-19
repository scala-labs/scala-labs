package com.xebia.cooking

import org.scalatest._
import org.scalatest.junit.JUnit3Suite

class SeasonsTest extends JUnit3Suite {
	def testSummerIsConstructedFromSummerString() {
		val summer = Seasons.parse("summer")
		expect(Seasons.summer) { summer }
	}
} 