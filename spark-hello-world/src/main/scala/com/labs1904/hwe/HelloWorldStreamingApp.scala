package com.labs1904.hwe

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.streaming.MemoryStream
import org.apache.spark.sql.streaming.{OutputMode, StreamingQuery, Trigger}

import java.io.File
import scala.util.Random

/**
 * Spark Structured Streaming app
 */
object HelloWorldStreamingApp {
  lazy val logger: Logger = Logger.getLogger(this.getClass)
  val jobName = "WordCountStreamingApp"

  def main(args: Array[String]): Unit = {
    logger.info(s"$jobName starting...")

    try {
      // Initialize the Spark framework for running locally on your pc
      val spark = SparkSession.builder()
        .appName(jobName)
        .config("spark.sql.shuffle.partitions", "3") // limit the number of partitions, otherwise things get slow
        .master("local[*]") // local execution, the '*' means use all the cpu cores for processing
        .getOrCreate()

      // For testing, it is much easier to test with everything locally. That way we don't have to go connect
      // to a lot of other systems just to see if our app works.
      // We'll create a dummy stream from memory that we populate manually.
      import spark.sqlContext.implicits._
      val inputStream = new MemoryStream[String](1, spark.sqlContext)
      val dataset = inputStream.toDS()

      // Print data out to the console every 5 seconds
      val query = dataset.writeStream
        .outputMode(OutputMode.Append())
        .format("console")
        .trigger(Trigger.ProcessingTime("5 seconds"))
        .start()

      // Start our data generator in the background
      val sentenceGenerator = new SentenceGeneratorRunnable(query, inputStream)
      new Thread(sentenceGenerator).start()

      query.awaitTermination()
    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }
  }
}

class SentenceGeneratorRunnable(query: StreamingQuery, stream: MemoryStream[String]) extends Runnable {
  lazy val logger: Logger = Logger.getLogger(this.getClass)

  override def run(): Unit = {
    logger.info("Random sentence generator starting...")
    val random = new Random(seed=42)
    val src  = scala.io.Source.fromFile("src/main/resources/sentences.txt")
    val lines = src.getLines().toArray
    src.close()
    while (query.isActive) {
      // pick a random line to emit
      stream.addData(lines(random.nextInt(lines.length)))
      Thread.sleep(500)
    }
  }
}