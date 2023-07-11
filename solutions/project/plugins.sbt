// Actually IDE specific settings belong into ~/.sbt/,
// but in order to ease the setup for the training we put the following here:

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.2")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")
