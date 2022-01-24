package com.labs1904.spark

import org.apache.log4j.Logger
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * Spark Structured Streaming app
 *
 */
object StreamingPipeline {
  lazy val logger: Logger = Logger.getLogger(this.getClass)
  val jobName = "StreamingPipeline"

  def main(args: Array[String]): Unit = {
    try {
      val spark = SparkSession.builder().config("spark.sql.shuffle.partitions", "3").appName(jobName).master("local[*]").getOrCreate()
      // TODO: change bootstrap servers to your kafka brokers
      val bootstrapServers = "localhost:9092"

      import spark.implicits._

      val ds = spark
        .readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", bootstrapServers)
        .option("subscribe", "change-me")
        .option("startingOffsets", "earliest")
        .option("maxOffsetsPerTrigger", "20")
        .load()
        .selectExpr("CAST(value AS STRING)").as[String]

      ds.printSchema()

      val query = ds.writeStream
        .outputMode(OutputMode.Append())
        .format("console")
        .option("truncate", false)
        .trigger(Trigger.ProcessingTime("5 seconds"))
        .start()

      query.awaitTermination()
    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }
  }
}
