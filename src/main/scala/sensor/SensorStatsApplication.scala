package sensor

import org.apache.log4j.Logger
import sensor.conf.ConfigMapper
import sensor.service.TrackerService

object SensorStatsApplication {
  val logger = Logger.getLogger(this.getClass.getName)

  def main(args: Array[String]) {
    if (!args(0).isEmpty) {
      val tracker = new TrackerService(
        new ConfigMapper().getConfig, args(0)
      )
      tracker.track()
    } else {
      println("Provide directory path as input parameter.")
      logger.warn("Provide directory path as input parameter.")
    }
  }
}
