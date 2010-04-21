import sbt._

class ScalaLabsProject(info: ProjectInfo) extends DefaultProject(info) {
  val configgyRepo = "Configgy" at "http://www.lag.net/repo"
  val codehaus = "Codehaus" at "http://repository.codehaus.org"
  val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

  val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
  val jodatime = "joda-time" % "joda-time" % "1.6"
  val commonsHttp = "commons-httpclient" % "commons-httpclient" % "3.1"
  val configgy = "net.lag" % "configgy" % "1.5.2" % "compile"

  //val scalatest = "org.scalatest" % "scalatest" % "1.0" % "test"
  val scalatest = "org.scalatest" % "scalatest" % "1.0.1-for-scala-2.8.0.RC1-SNAPSHOT" % "test"
//  val scalatest = "org.scalatest" % "scalatest" % "1.0.1-SNAPSHOT" % "test"
  //http://scala-tools.org/repo-snapshots/org/scalatest/scalatest/1.0.1-for-scala-2.8.0.RC1-SNAPSHOT/
  val junit = "junit" % "junit" % "4.7" % "test"

}