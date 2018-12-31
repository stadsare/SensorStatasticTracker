package sensor.service

import java.nio.file.{Files, Paths}

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{avg, col, max, min}
import sensor.conf.Configuration

/**
  * Service class
  * Using spark dataframe calculating the stats for the given input files
  */
class TrackerService (conf: Configuration, inputDirPath: String) extends SparkSessionWrapper {

  val humidity_col_str = "humidity"
  /*
    Method prints output stats on console
   */
  def track(): Unit = {
    // Initializing required parameters
    val inputPath = Paths.get(inputDirPath)
    val delimiter = conf.delimiter
    val header = conf.header.toString

    // Reading files from given path to create dataframe
    val records = spark.read
      .option("header", header)
      .option("delimiter", delimiter)
      .option("inferSchema", "true")
      .csv(inputPath.toFile.getAbsolutePath).cache()

    // Calculating the stats
    val noOfFilesProcessed = Files.list(inputPath).count()
    val noOfProcessedMeasurements = records.count()
    val noOfFailedMeasurements = records.filter(col(humidity_col_str).isNaN).count()

    // Calculating aggregate stats for given input per sensor-id
    val finalDf = calculateStats(records)

    // Printing final output stats on console
    print(Report.generate(noOfFilesProcessed, noOfProcessedMeasurements, noOfFailedMeasurements, finalDf.collect()))
  }

  // Calculating aggregate stats for given input per sensor-id
  def calculateStats(records: DataFrame): DataFrame ={
    val mapToNull = records.columns.map((_, "null")).toMap
    val recordsToProcessDf = records.na.fill(mapToNull)
    val resultDf = recordsToProcessDf.groupBy("sensor-id")
      .agg(
        min(humidity_col_str) as "min",
        avg(humidity_col_str) as "avg",
        max(humidity_col_str) as "max")
      .orderBy(col("avg").desc)
    val mapToNaN = resultDf.columns.map((_, "NaN")).toMap
    resultDf.na.fill(mapToNaN)
  }
}
