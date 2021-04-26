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
      val spark = SparkSession.builder().appName(jobName).master("local[*]").getOrCreate()
      // TODO: change bootstrap servers to your kafka brokers
      val bootstrapServers = "localhost:9092"

      val df = spark
        .readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", bootstrapServers)
        .option("subscribe", "change-me")
        .option("startingOffsets", "earliest")
        .option("maxOffsetsPerTrigger", "20")
        .load()
        .selectExpr("CAST(value AS STRING)")

      df.printSchema()

      val query = df.writeStream
        .outputMode(OutputMode.Append())
        .format("console")
        .trigger(Trigger.ProcessingTime("5 seconds"))
        .start()

      query.awaitTermination()
    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }
  }
}
