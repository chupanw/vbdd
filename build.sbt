scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

//libraryDependencies += "com.storm-enroute" %% "scalameter" % "0.7" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.0" % "test"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test exclude("junit", "junit-dep")
)

libraryDependencies += "com.google.code.findbugs" % "jsr305" % "3.0.1"



parallelExecution in Test := false

scalacOptions += "-unchecked"

