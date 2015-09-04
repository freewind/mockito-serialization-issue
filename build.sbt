name := "mockito-serialization-issue"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.mockito" % "mockito-all" % "1.10.19",
  "org.scalamock" %% "scalamock-core" % "3.2.2",
  "org.scalatest" %% "scalatest-all" % "3.0.0-M7",
  "org.scalamock" % "scalamock-scalatest-support_2.11" % "3.2.1",
  "org.scalactic" %% 	"scalactic" % "3.0.0-M7"
).map(_ withSources())

