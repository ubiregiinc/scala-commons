scalaVersion := "2.10.1"

version := "0.1-SNAPSHOT"

organization := "com.ubiregi"

name := "scala-commons"

licenses := Seq("MIT License" -> url("http://opensource.org/licenses/MIT"))

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-reflect" % _)

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "1.14" % "test"
)

scalacOptions ++= Seq(
  "-unchecked", "-deprecation", "-Xlint", "-language:experimental.macros"
)
