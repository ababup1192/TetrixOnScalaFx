name := """tetrix"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "8.0.60-R9"
)

  // "com.typesafe.akka" %% "akka-actor" % "2.3.11"

// Fork a new JVM for 'run' and 'test:run',  to avoid JavaFX double initialization problems
fork := true
