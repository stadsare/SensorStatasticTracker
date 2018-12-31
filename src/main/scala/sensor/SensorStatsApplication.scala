package sensor

import sensor.conf.ConfigMapper
import sensor.service.TrackerService

object SensorStatsApplication {

  def main(args: Array[String]) {
    val tracker = new TrackerService(
      new ConfigMapper().getConfig, args(0)
    )
    tracker.track()
  }
}
