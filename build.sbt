name := "flickr-api"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  "org.scala-lang" % "scala-xml" % "2.11.0-M4"
)

//Test dependencies
libraryDependencies ++= Seq(
  "org.specs2" % "specs2_2.11" % "3.7" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
