import Dependencies._

ThisBuild / scalaVersion    := "2.11.8"    // 2.11.8 for Hive Use
ThisBuild / version         := "0.1.0-Test"

lazy val root = (project in file("."))
   .settings(
       name := "testing",
       libraryDependencies += scalaTest % Test
   )
