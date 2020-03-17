pgpPassphrase := Some(getEnvVar("PGP_PASSPHRASE").getOrElse("").toCharArray)

lazy val root = project
  .in(file("."))
  .dependsOn(core)
  .aggregate(core)
  .settings(pluginSettings: _*)

lazy val core = project
  .in(file("core"))
  .settings(coreSettings: _*)