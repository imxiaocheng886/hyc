package child

import org.apache.spark.sql.{DataFrame, SparkSession}


object SparkSQlChild1 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("SparkSQlChild1")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    val df: DataFrame = spark.read.format("jdbc")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("url", "jdbc:mysql://localhost:3306/child?characterEncoding=utf8&useSSL=false")
      .option("user", "root")
      .option("password", "root")
      .option("dbtable", "tb_et_directorstreet_set")
      .load()
    df.createOrReplaceTempView("tb_et_directorstreet_set")
    //38街道2022年的每月走访次数趋势图
    spark.sql("select et_directorstreet_set_one_visitsum,et_directorstreet_set_two_visitsum,et_directorstreet_set_three_visitsum,\n" +
      "et_directorstreet_set_four_visitsum,et_directorstreet_set_five_visitsum,et_directorstreet_set_six_visitsum," +
      "et_directorstreet_set_seven_visitsum,et_directorstreet_set_eight_visitsum,et_directorstreet_set_nine_visitsum," +
      "et_directorstreet_set_ten_visitsum,et_directorstreet_set_eleven_visitsum,et_directorstreet_set_twelve_visitsum " +
      "from tb_et_directorstreet_set where et_directorstreet_set_id=38 and et_directorstreet_set_year=2022").coalesce(1)
      .rdd.saveAsTextFile("child1")
    spark.stop()
  }
}
