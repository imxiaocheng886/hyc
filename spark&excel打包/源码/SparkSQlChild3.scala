package child

import org.apache.spark.sql.{DataFrame, SparkSession}


object SparkSQlChild3 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("SparkSQlChild3")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    val df: DataFrame = spark.read.format("jdbc")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("url", "jdbc:mysql://localhost:3306/child?characterEncoding=utf8&useSSL=false")
      .option("user", "root")
      .option("password", "root")
      .option("dbtable", "tb_et_supervisorstreet_ew_set")
      .load()
    df.createOrReplaceTempView("tb_et_supervisorstreet_ew_set")
    //.网格id18、2022年的每月预警总处理数趋势
    spark.sql("select et_supervisorstreet_ew_set_one_dealsum,et_supervisorstreet_ew_set_two_dealsum,et_supervisorstreet_ew_set_three_dealsum,et_supervisorstreet_ew_set_four_dealsum,et_supervisorstreet_ew_set_five_dealsum,et_supervisorstreet_ew_set_six_dealsum,et_supervisorstreet_ew_set_seven_dealsum,et_supervisorstreet_ew_set_eight_dealsum,et_supervisorstreet_ew_set_nine_dealsum,et_supervisorstreet_ew_set_ten_dealsum,et_supervisorstreet_ew_set_eleven_dealsum," +
      "et_supervisorstreet_ew_set_twelve_dealsum from tb_et_supervisorstreet_ew_set where et_supervisorstreet_ew_set_street_id=18 " +
      "and et_supervisorstreet_ew_set_year=2022").coalesce(1)
      .rdd.saveAsTextFile("child3")
    spark.stop()
  }
}
