import sbt._

class LiftProject(info: ProjectInfo) extends ParentProject(info) {

  lazy val spa = project("spa", "spa",        new SpaProject(_))
  lazy val web = project("web", "web",        new WebProject(_))

  class SpaProject(info: ProjectInfo) extends DefaultProject(info) with BasicProjectSettings
  class WebProject(info: ProjectInfo) extends DefaultWebProject(info) with BasicProjectSettings

}

trait BasicProjectSettings extends BasicDependencyProject {
  // Add Maven Local repository for SBT to search for (disable if this doesn't suit you)
  val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"

  // Add snapshot repo, since Lift SNAPSHOT in use
  val snapshots = ScalaToolsSnapshots
}
