lazy val root = (project in file("."))
  .settings(name := "ai-trader")
  .aggregate(marketApi, marketImpl)
  .settings(commonSettings: _*)

organization in ThisBuild := "com.lejoow"

scalaVersion in ThisBuild := "2.11.8"

val playJsonDerivedCodecs = "org.julienrf" %% "play-json-derived-codecs" % "3.3"
val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val cassandraDriverExtras = "com.datastax.cassandra" % "cassandra-driver-extras" % "3.1.2"
val lagomScaladslPlayJson = "com.lightbend.lagom" % "lagom-scaladsl-play-json_2.11" % "1.3.0"
val scalac = "org.scalactic" %% "scalactic" % "3.0.1"
val playNettyServer = "com.typesafe.play" % "play-netty-server_2.11" % "2.5.4"
val cassandra = "com.cassandra."
val async = "org.scala-lang.modules" % "scala-async_2.11" % "0.9.6"
val jodaMoney = "com.github.nscala-money" %% "nscala-money" % "0.12.0"
val jodaMoneyJson = "com.github.nscala-money" %% "nscala-money-play-json" % "0.12.0"
val playS3 = "net.kaliber" %% "play-s3" % "8.0.0"
val better_files = "com.github.pathikrit" % "better-files_2.11" % "2.17.1"
val scala_csv = "com.github.tototoshi" %% "scala-csv" % "1.3.4"
val scalaBreeze = "org.scalanlp" %% "breeze" % "0.13"
val mllib = "org.apache.spark" %% "spark-mllib" % "1.3.1"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

lazy val marketApi = (project in file("market-api"))
  .settings(commonSettings: _*)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      playJsonDerivedCodecs
    )
  )

lazy val marketImpl = (project in file("market-impl"))
  .settings(commonSettings: _*)
  .enablePlugins(LagomScala)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslTestKit,
      lagomScaladslKafkaBroker,
      "com.datastax.cassandra" % "cassandra-driver-extras" % "3.0.0",
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(marketApi)

def commonSettings: Seq[Setting[_]] = Seq(
)

lagomCassandraCleanOnStart in ThisBuild := true

