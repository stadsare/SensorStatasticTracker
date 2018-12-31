package sensor.service

import org.scalatest.FunSpec

class TrackerServiceTest extends FunSpec with SparkSessionTestWrapper {

  it("validate if expected report dataframe is correct") {
    val expectedDF = spark.read
      .option("header", "true")
      .option("delimiter", ",")
      .option("inferSchema", "true")
      .csv("src/test/scala/resource/output/report.csv")

    val records = spark.read
      .option("header", "true")
      .option("delimiter", ",")
      .option("inferSchema", "true")
      .csv("src/test/scala/resource/input/")

    val actualDf = new TrackerService(null, "").calculateStats(records)
    assert(actualDf.collect().sameElements(expectedDF.collect()))
  }
}
