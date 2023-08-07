package child

import org.apache.spark.sql.{DataFrame, SparkSession}


object SparkSQlChild2 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("SparkSQlChild2")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    val df: DataFrame = spark.read.format("jdbc")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("url", "jdbc:mysql://localhost:3306/child?characterEncoding=utf8&useSSL=false")
      .option("user", "root")
      .option("password", "root")
      .option("dbtable", "tb_et_checkvisit")
      .load()
    df.createOrReplaceTempView("tb_et_checkvisit")
    //.统计现阶段物质生活是否正常  A.正常     B.异常 占比分析
    spark.sql("select et_checkvisit_xjdwzshsfzc key_tmp,count(1) counts from tb_et_checkvisit group by et_checkvisit_xjdwzshsfzc").coalesce(1)
      .rdd.saveAsTextFile("child2-1")
    //统计你平时一周与家人的沟通频次是多少？A、0-3次  B、4-7次  C、7次以上 占比分析
    spark.sql("select et_checkvisit_npsyzyjrdgspl key_tmp,count(1) counts from tb_et_checkvisit group by et_checkvisit_npsyzyjrdgspl").coalesce(1)
      .rdd.saveAsTextFile("child2-2")
    spark.stop()
  }
}
