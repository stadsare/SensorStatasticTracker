name := "SensorStatisticTracker"
version := "0.1"
scalaVersion := "2.11.8"

val sparkVersion = "2.4.0"

libraryDependencies ++= Seq(
  "org.mockito" % "mockito-core" % "2.11.0" % "test",
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "com.github.pureconfig" %% "pureconfig" % "0.7.2",
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
  "log4j" % "log4j" % "1.2.14"
)
