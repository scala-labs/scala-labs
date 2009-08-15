/*
 * MyProject.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import sbt._

class ItrProject(info: ProjectInfo) extends DefaultWebProject(info) {
  override def useMavenConfigurations = true

  val specsRepo = "Specs Repository" at "http://specs.googlecode.com/svn/maven2/"
  val scalacheck = "org.scala-tools.testing" % "scalacheck" % "1.5"
  val mockito = "org.mockito" % "mockito-core" % "1.7"
  val specs = "org.scala-tools.testing" % "specs" % "1.4.4"
  val lift = "net.liftweb" % "lift-core" % "1.1-M4" % "compile->default"
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.17" % "test->default"
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided->default"
  val derby = "org.apache.derby" % "derby" % "10.2.2.0" % "runtime->default"
  val junit = "junit" % "junit" % "3.8.1" % "test->default"

  // required because Ivy doesn't pull repositories from poms
  val smackRepo = "m2-repository-smack" at "http://maven.reucon.com/public"
}