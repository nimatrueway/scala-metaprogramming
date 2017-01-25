name := "BlackAndWhiteBoxMacroSample"
version := "1.0"
scalaVersion := "2.11.8"


lazy val macros = (project in file("macros")).settings(Seq(
  scalaVersion := "2.11.8",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
))

dependsOn(macros)

macroRevolver.MacroRevolverPlugin.testCleanse
