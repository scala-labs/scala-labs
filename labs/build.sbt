name := "ScalaLabs"

organization := "Xebia B.V."

version := "1.0"

scalaVersion := "2.10.2"

resolvers ++= Seq("Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository",
	"Signpost releases" at "https://oss.sonatype.org/content/repositories/signpost-releases/")

libraryDependencies ++= Seq("joda-time" % "joda-time" % "1.6",
	"org.apache.httpcomponents" % "httpclient" % "4.1.1",
	"javax.persistence" % "persistence-api" % "1.0",
	"org.scala-libs" %% "scalajpa" % "1.4",
	"oauth.signpost" % "signpost-core" % "1.2",
	"oauth.signpost" % "signpost-commonshttp4" % "1.2",
        "org.scala-lang" % "scala-actors" % "2.10.3",
	"org.scalatest" %% "scalatest" % "2.0" % "test",
	"org.specs2" %% "specs2" % "2.3.7" % "test",
	"junit" % "junit" % "4.7" % "test",
	"hsqldb" % "hsqldb" % "1.8.0.1" % "test",
	"org.hibernate" % "hibernate-entitymanager" % "3.4.0.GA",
	"org.slf4j" % "slf4j-simple" % "1.4.2")

