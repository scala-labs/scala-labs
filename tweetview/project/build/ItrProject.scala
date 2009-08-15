/*
 * MyProject.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import sbt._

class ItrProject(info: ProjectInfo) extends DefaultProject(info) {
  val specsRepo = "Specs Repository" at "http://specs.googlecode.com/svn/maven2/"

  val specs = "org.scala-tools.testing" % "specs" % "1.4.4"
  val scalacheck = "org.scala-tools.testing" % "scalacheck" % "1.5"
  val mockito = "org.mockito" % "mockito-core" % "1.7"
  val junit = "junit" % "junit" % "4.5"
  val lift = "net.liftweb" % "lift-core" % "1.1-M4"
}
