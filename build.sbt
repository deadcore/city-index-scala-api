name := "city-index-api"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "org.json4s" %% "json4s-native" % "3.5.0",
  "org.scalatest" %% "scalatest" % "3.0.1",
  "io.reactivex" % "rxscala_2.11" % "0.26.5",
  "org.mockito" % "mockito-all" % "1.10.19" % Test
).map(_
  .exclude("org.slf4j", "slf4j-jdk14")
  .exclude("org.slf4j", "slf4j-nop")
) ++ Seq(
  "ch.qos.logback" % "logback-core" % "1.2.1",
  "ch.qos.logback" % "logback-classic" % "1.2.1",
  "org.codehaus.janino" % "janino" % "2.7.8"
)