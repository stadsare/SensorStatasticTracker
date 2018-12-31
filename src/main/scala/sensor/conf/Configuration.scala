package sensor.conf

/**
  * Configuration mapping class from applications.conf
  * @param delimiter delimiter in input file
  * @param header header present flag
  */
case class Configuration(delimiter: String, header: Boolean)
