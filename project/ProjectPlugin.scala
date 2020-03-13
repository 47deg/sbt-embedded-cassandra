import sbt.Keys._
import sbt._
import sbtorgpolicies.OrgPoliciesPlugin
import sbtorgpolicies.OrgPoliciesPlugin.autoImport._
import sbtorgpolicies.templates.badges._
import sbtorgpolicies.model._
import sbtorgpolicies.runnable.syntax._

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = OrgPoliciesPlugin

  object autoImport {

    lazy val pluginSettings: Seq[Def.Setting[_]] = Seq(
      moduleName := "sbt-embedded-cassandra",
      sbtPlugin := true,
      scalaVersion := scalac.`2.12`,
      scalacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-unchecked"),
      orgBadgeListSetting := List(
        TravisBadge.apply,
        CodecovBadge.apply,
        MavenCentralBadge.apply,
        LicenseBadge.apply,
        GitHubIssuesBadge.apply
      )
    )

    lazy val coreSettings: Seq[Def.Setting[_]] = Seq(
      moduleName := "embedded-cassandra-core",
      sbtPlugin := false,
      resolvers += Resolver.typesafeIvyRepo("releases").withName("typesafe-alt-ivy-releases"),
      scalaVersion := scalac.`2.12`,
      libraryDependencies ++= Seq(
        %%("org-policies-core"),
        %("cassandra-driver-core"),
        "org.apache.cassandra" % "cassandra-all" % "3.11.6"
      ),
    )

  }

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    name := "sbt-embedded-cassandra",
    orgProjectName := "sbt-embedded-cassandra",
    orgGithubSetting := GitHubSettings(
      organization = "47degrees",
      project = "sbt-embedded-cassandra",
      organizationName = "47 Degrees",
      groupId = "com.47deg",
      organizationHomePage = url("http://47deg.com"),
      organizationEmail = "hello@47deg.com"
    ),
    orgScriptTaskListSetting := List(
      orgCheckSettings.asRunnableItem,
      "clean".asRunnableItemFull,
      "compile".asRunnableItemFull,
      "test".asRunnableItemFull
    ),
    startYear := Option(2017),
    scalaOrganization := "org.scala-lang"
  )

}
