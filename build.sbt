name := "samza-poc"

version := "0.1"

scalaVersion := "2.12.10"

val samzaVersion = "1.0.0"

val samzaCore = "org.apache.samza" %% "samza-core" % samzaVersion
val samzaKafka = "org.apache.samza" %% "samza-kafka" % samzaVersion
val typesafe = "com.typesafe" % "config" % "1.4.0"

libraryDependencies ++= Seq(
  samzaCore
  , samzaKafka
)
