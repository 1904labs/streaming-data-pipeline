package com.labs1904.hwe

import com.labs1904.hwe.WordCountBatchApp.splitSentenceIntoWords
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.log4j.Logger
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StringType, StructType}

import java.util.Properties

/**
 * Spark Structured Streaming app
 */
object WordCountStreamingApp {
  lazy val logger: Logger = Logger.getLogger(this.getClass)
  val jobName = "WordCountStreamingApp"
  // TODO: define the schema for parsing data from Kafka
  // confused - when I tried to do this, it said I couldn't, basically.
  // I must've been doing something wrong...?
  // But also - do I even need to do this? Seems like no, not really -
  // but then what is the benefit of it / under what circumstances would / should I do it?

  val bootstrapServer : String = "b-2-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196,b-1-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196,b-3-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196"
  val username: String = "hwe"
  val password: String = "1904labs"
  val Topic: String = "word-count"

  //Use this for Windows
  //val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  def main(args: Array[String]): Unit = {
    logger.info(s"$jobName starting...")

    try {
      val spark = SparkSession.builder()
        .appName(jobName)
        .config("spark.sql.shuffle.partitions", "3")
        .master("local[*]")
        .getOrCreate()

      import spark.implicits._

      val sentences = spark
        .readStream
        .format("kafka")
        .option("maxOffsetsPerTrigger", 10)
        .option("kafka.bootstrap.servers", bootstrapServer)
        .option("subscribe", "word-count")
        .option("kafka.acks", "1")
        .option("kafka.key.serializer", classOf[StringSerializer].getName)
        .option("kafka.value.serializer", classOf[StringSerializer].getName)
        .option("startingOffsets","earliest")
        .option("kafka.security.protocol", "SASL_SSL")
        .option("kafka.sasl.mechanism", "SCRAM-SHA-512")
        .option("kafka.ssl.truststore.location", trustStore)
        .option("kafka.sasl.jaas.config", getScramAuthString(username, password))
        .load()
        .selectExpr("CAST(value AS STRING)").as[String]
//        .withColumn("timestamp", current_timestamp())

      sentences.printSchema

//      // TODO: implement me

      // this does a running total of counts - so eventually the word "the" is at the top
      // since it's the most common word across all batches, over time
      val counts = sentences
        .flatMap(row => splitSentenceIntoWords(row))
        .groupBy("value").count()
        .sort(desc("count"))
        .limit(10)

      val query = counts.writeStream
        .outputMode(OutputMode.Complete())
        .format("console")
        .trigger(Trigger.ProcessingTime("5 seconds"))
        .start()

      // This was me misunderstanding the directions - the correct code (more or less) is what I accidentally did at first, above
//      val windowedCounts = sentences
//        .flatMap(row => splitSentenceIntoWords(row))
//        .withColumn("timestamp", current_timestamp())
//        .withWatermark("timestamp", "5 seconds")
//        .groupBy(
//          window($"timestamp", "5 seconds", "5 seconds"),
//          $"value")
//        .count()
////        .limit(10)
////        .sort(desc("count"))
//
//      val query = windowedCounts.writeStream
//        .outputMode(OutputMode.Append())
//        .format("console")
//        .trigger(Trigger.ProcessingTime("5 seconds"))
//        .start()

      query.awaitTermination()
    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }

  }

  def getScramAuthString(username: String, password: String) = {
    s"""org.apache.kafka.common.security.scram.ScramLoginModule required
   username=\"$username\"
   password=\"$password\";"""
  }

  def splitSentenceIntoWords(sentence: String): Array[String] = {
    sentence.toLowerCase.replaceAll("[^a-z ]+", "").split(" ")
  }


}
