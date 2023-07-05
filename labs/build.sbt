import sbt.Path

name := "ScalaLabs"

organization := "Xebia B.V."

version := "1.0"

scalaVersion := "2.13.1"

scalacOptions ++= Seq("-unchecked", "-deprecation")

resolvers ++= Seq(
  "Local Maven Repository" at "file:///" + Path.userHome + "/.m2/repository",
  "Signpost releases" at "https://oss.sonatype.org/content/repositories/signpost-releases/"
)

// You should be able to use the following to read all dependencies from the pom.xml file, but somehow those aren't picked up.
// see: https://github.com/harrah/xsbt/wiki/Library-Management
// externalPom()

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.12.5",
  "org.hsqldb" % "hsqldb" % "2.7.2" % "test",
  "org.json4s" %% "json4s-native" % "4.0.6",
  "org.mockito" % "mockito-core" % "5.4.0" % "test",
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0",
  "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
  "org.scalatest" %% "scalatest" % "3.2.16" % "test",
  "org.slf4j" % "slf4j-simple" % "2.0.7",
  "org.specs2" %% "specs2-core" % "4.20.0" % "test",
  "org.specs2" %% "specs2-junit" % "4.20.0" % "test",
  "org.specs2" %% "specs2-mock" % "4.20.0" % "test"
)
