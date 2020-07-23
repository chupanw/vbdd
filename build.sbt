name := "vbdd"
organization := "edu.cmu.cs.varex"
version := "0.2.0"

scalaVersion := "2.12.12"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

//libraryDependencies += "de.fosd.typechef" %% "featureexprlib" % "0.4.1" % "test"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test exclude("junit", "junit-dep")
)

libraryDependencies += "com.google.code.findbugs" % "jsr305" % "3.0.1"

javaOptions in Test += "-ea"
fork in Test := true
parallelExecution in Test := false

scalacOptions += "-unchecked"

