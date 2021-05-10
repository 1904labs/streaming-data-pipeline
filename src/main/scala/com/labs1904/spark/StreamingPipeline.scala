package com.labs1904.spark

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{ConnectionFactory, Get}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.log4j.Logger
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.{Dataset, SparkSession}

/**
 * Spark Structured Streaming app
 *
 */
object StreamingPipeline {
  lazy val logger: Logger = Logger.getLogger(this.getClass)

  implicit def stringToBytes(str: String): Array[Byte] = Bytes.toBytes(str)

  implicit def bytesToString(bytes: Array[Byte]): String = Bytes.toString(bytes)

  val jobName = "StreamingPipeline"

  val bootstrapServers = "35.239.241.212:9092,35.239.230.132:9092,34.69.66.216:9092"

  def main(args: Array[String]): Unit = {
    try {
      val spark = SparkSession.builder()
        .config("spark.hadoop.dfs.client.use.datanode.hostname", "true")
        .config("spark.hadoop.fs.defaultFS", "hdfs://manager.hourswith.expert:8020")
        .appName(jobName)
        .master("local[*]")
        .getOrCreate()

      import spark.implicits._

      val ds = spark
        .readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", bootstrapServers)
        .option("subscribe", "reviews-as-tabs")
        .option("startingOffsets", "earliest")
        .option("maxOffsetsPerTrigger", "100")
        .load()
        .selectExpr("CAST(value AS STRING)").as[String]

      ds.printSchema()

      // Transform from a tsv into a Review case class.
      val reviews = ds.map(csvLine => {
        val csvArray = csvLine.split("\t")
        Review(
          csvArray(0),
          csvArray(1),
          csvArray(2),
          csvArray(3),
          csvArray(4),
          csvArray(5),
          csvArray(6),
          csvArray(7),
          csvArray(8),
          csvArray(9),
          csvArray(10),
          csvArray(11),
          csvArray(12),
          csvArray(13),
          csvArray(14)
        )
      })

      // Combine the review data with the user data.
      //
      val enriched = reviews.mapPartitions(p => {
        // Open Hbase Connection
        val conf = HBaseConfiguration.create()
        conf.set("hbase.zookeeper.quorum", "cdh01.hourswith.expert:2181,cdh02.hourswith.expert:2181,cdh03.hourswith.expert:2181")
        val connection = ConnectionFactory.createConnection(conf)

        val table = connection.getTable(TableName.valueOf("shared:users"))

        val iterator = p.map(r => {
          val get = new Get(r.customer_id).addFamily("f1")
          val result = table.get(get)
          val name = result.getValue("f1", "name")
          val birthdate = result.getValue("f1", "birthdate")
          val mail = result.getValue("f1", "mail")
          val sex = result.getValue("f1", "sex")
          val username = result.getValue("f1", "username")
          EnrichedReview(r.marketplace, r.customer_id, r.review_id, r.product_id, r.product_parent, r.product_title, r.product_category, r.star_rating, r.helpful_votes, r.total_votes, r.vine, r.verified_purchase, r.review_headline, r.review_body, r.review_date, name, birthdate, mail, sex, username)
        }).toList

        // close connection
        connection.close()

        iterator.iterator
      })

      /*
      // WriteStream Questions:
      // What file formats did you save as?
      // Did anyone partition their data?
      // What is the benefit of partitioning your data?
      */
      val query = enriched.writeStream
        .outputMode(OutputMode.Append())
        .format("json")
        .partitionBy("star_rating")
        .option("path", "/user/wfarrell/reviews")
        .option("checkpointLocation", "/user/wfarrell/reviews_checkpoint")
        .trigger(Trigger.ProcessingTime("5 seconds"))
        .start()

      query.awaitTermination()
    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }
  }
}

// Add in partitionBy

//val query = enriched.writeStream
//.outputMode(OutputMode.Append())
//.format("parquet")
//.option("path", "/user/wfarrell/reviews-partitioned")
//.option("checkpointLocation", "/user/wfarrell/reviews-partitioned-checkpoint")
//.trigger(Trigger.ProcessingTime("5 seconds"))
//.start()

