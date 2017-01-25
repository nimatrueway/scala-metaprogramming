name := "AnnotationMacroScalametaSample"
version := "1.0"

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  resolvers += Resolver.bintrayIvyRepo("scalameta", "maven"),
  addCompilerPlugin("org.scalameta" % "paradise" % "4.0.0.142" cross CrossVersion.full)
)

lazy val macros = (project in file("macros")).settings(Seq(
  scalaVersion := "2.11.8",
  libraryDependencies += "org.scalameta" %% "scalameta" % "2.0.0.566"
) ++ commonSettings)

projectSettings ++ commonSettings
dependsOn(macros)

macroRevolver.MacroRevolverPlugin.testCleanse