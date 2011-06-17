import sbt._
import de.element34.sbteclipsify._
import com.github.olim7t.sbtscalariform._

class JunctionProject(info: ProjectInfo) extends DefaultProject(info)
with Eclipsify with IdeaProject with AkkaKernelProject with ScalariformPlugin {
  override def repositories = Set("akka" at "http://akka.io/repository", "repo1" at "http://repo1.maven.org/maven2")

  val akkaRemote = akkaModule("remote")
  val akkaHttp = akkaModule("http")
  val akkaCamel = akkaModule("camel")
  val akkaTestKit = akkaModule("testkit")
  lazy val logback = "ch.qos.logback" % "logback-classic" % "0.9.28" % "runtime"
  // for ui
  val swing = "org.scala-lang" % "scala-swing" % this.buildScalaVersion % "compile"

  val scalatest = "org.scalatest" %% "scalatest" % "1.6.1.RC1" % "test->default"
  val junit = "junit" % "junit" % "3.8.1" % "test->default"

  override def formatBeforeCompiling = false

  lazy val distGui = task {
    FileUtilities.copyFile("src" / "main" / "resources" / "akka-gui.conf", distConfigPath / "akka-gui.conf", log)
    FileUtilities.copyFile("src" / "main" / "scripts" / "gui", distBinPath / "gui", log)
    doCMD("chmod 775 " + distBinPath / "gui")
    FileUtilities.copyFile("src" / "main" / "scripts" / "gui.bat", distBinPath / "gui.bat", log)
  } dependsOn (dist) describedAs ("distribute gui.")

  /**
   * run commandline
   */
  def doCMD(cmd: String): Option[String] = {
    try {
      //call directly the runtime.exec doesn't works always (depending on command)
      //so use cmd2
      val cmd2 = Array[String]("/bin/sh", "-c", cmd)
      val proc = Runtime.getRuntime().exec(cmd2)
      val exitVal = proc.waitFor();
      if (exitVal != 0) {
        log.error("Can't run exitcode " + exitVal + " from: " + cmd)
        val stream = new java.io.BufferedReader(new java.io.InputStreamReader(proc.getErrorStream))
        try {
          var line = stream.readLine
          while (line != null) {
            log.error(line)
            line = stream.readLine
          }
        } finally {
          stream.close()
        }
      } else {
        val streamOut = new java.io.LineNumberReader(new java.io.InputStreamReader(proc.getInputStream))
        try {
        var line = streamOut.readLine
        return Some(line)
        } finally {
          streamOut.close()
        }
      }
    } catch {
      case t: Throwable => {
        log.error("command failed")
        t.printStackTrace();
      }
    }
    return None
  }

}

