package com.labs1904.hwe

import org.apache.log4j.Logger
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{StringType, StructType}

object WordCountBatchApp {
  lazy val logger: Logger = Logger.getLogger(this.getClass)
  val jobName = "WordCountBatchApp"

  def main(args: Array[String]): Unit = {
    logger.info(s"$jobName starting...")
    try {
      val spark = SparkSession.builder()
        .appName(jobName)
        .config("spark.sql.shuffle.partitions", "3")
        .master("local[*]")
        .getOrCreate()
      import spark.implicits._

      val schema = new StructType().add("sentences", StringType, nullable=true)
      val sentences = spark.read.schema(schema).csv("src/main/resources/sentences.txt").as[String]
      sentences.printSchema()
      sentences.show()

      // TODO: implement me
      val words = sentences.flatMap(sentence => splitSentenceIntoWordStrings(sentence)).groupBy(col("value")).count()
      words.printSchema()
      words.show(50)

      //counts.foreach(wordCount=>println(wordCount))
    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }
  }

  // TODO: implement this function
  // HINT: you may have done this before in Scala practice...
  def splitSentenceIntoWordStrings(sentence: String): Array[String] = {
    val arrayOfStr: Array[String] = sentence.split("\\W+" )
    val strToLowerCase: Array[String] = arrayOfStr.map(word => word.toLowerCase())
    strToLowerCase
  }

}
