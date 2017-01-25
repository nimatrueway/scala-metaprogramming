name := "AnnotationMacroSample"
version := "1.0"

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
)

lazy val macros = (project in file("macros")).settings(Seq(
  scalaVersion := "2.11.8",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.8"
) ++ commonSettings)

projectSettings ++ commonSettings
dependsOn(macros)

macroRevolver.MacroRevolverPlugin.testCleanse