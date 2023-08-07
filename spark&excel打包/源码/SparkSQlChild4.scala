package child

import org.apache.spark.sql.{DataFrame, SparkSession}


object SparkSQlChild4 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("SparkSQlChild4")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    val df: DataFrame = spark.read.format("jdbc")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("url", "jdbc:mysql://localhost:3306/child?characterEncoding=utf8&useSSL=false")
      .option("user", "root")
      .option("password", "root")
      .option("dbtable", "tb_et_onlinecom")
      .load()
    df.createOrReplaceTempView("tb_et_onlinecom")
    //儿童主任线上竞赛11的分数区间占比
    spark.sql("select CASE WHEN et_onlinecom_score < 60 THEN '不及格' WHEN et_onlinecom_score between 60 and 70 THEN '及格' WHEN et_onlinecom_score between 70 and 90 THEN '中等' ELSE '优秀' END flag from tb_et_onlinecom where et_onlinecom_competition_id=11")
      .createOrReplaceTempView("table1")
    spark.sql("select flag,count(1) counts from table1 group by flag").coalesce(1)
      .rdd.saveAsTextFile("child4")
    spark.stop()
  }
}
