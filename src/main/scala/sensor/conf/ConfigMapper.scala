package sensor.conf

class ConfigMapper {
  def getConfig= pureconfig.loadConfigOrThrow[Configuration]
}
