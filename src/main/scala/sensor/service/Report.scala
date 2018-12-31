package sensor.service

import org.apache.spark.sql.Row

/**
  * Class to generate a final stats report to print
  */
object Report {
  def generate(noTotalFiles: Long, noTotalMeasurements: Long, noInvalidMeasurements: Long, result: Array[Row]): String = {
    s"""Num of processed files: $noTotalFiles
       |Num of processed measurements: $noTotalMeasurements
       |Num of failed measurements: $noInvalidMeasurements
       |
       |Sensors with highest avg humidity:
       |
       |sensor-id,min,avg,max"""
      .concat("\n" + result.map(_.mkString(",")).mkString("\n"))
      .stripMargin
  }
}
