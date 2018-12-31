package sensor.service

import org.apache.spark.sql.Row
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

class ReportTest extends FunSpec with ShouldMatchers {

  def f(s: String) : Row =
    Row.fromSeq(s.split(",").toSeq)

  describe("Report generator") {
    it("should generate a exact report") {
      val rows = new Array[Row](3)
      rows.update(0, f("s1,10,54,98"))
      rows.update(1, f("s2,78,82,88"))
      rows.update(2, f("s3, NaN, NaN, NaN"))
      val report = Report.generate(2, 7, 2, rows)
      report should be
        """Num of processed files: 2
          |Num of processed measurements: 7
          |Num of failed measurements: 2
          |
          |Sensors with highest avg humidity:
          |
          |sensor-id,min,avg,max
          |s1,10,54,98
          |s2,78,82,88
          |s3, NaN, NaN, NaN"""
          .stripMargin
    }
  }
}