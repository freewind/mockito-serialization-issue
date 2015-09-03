name := "mockito-serialization-issue"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.mockito" % "mockito-all" % "1.10.19"
).map(_ withSources())

