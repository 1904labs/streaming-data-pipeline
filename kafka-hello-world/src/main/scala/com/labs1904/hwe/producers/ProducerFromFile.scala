package com.labs1904.hwe.producers

import com.labs1904.hwe.util.Util.getScramAuthString
import org.apache.kafka.clients.admin.{AdminClient, CreateTopicsOptions, KafkaAdminClient, NewTopic}
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer

import java.io.{BufferedReader, File, FileReader}
import java.util.{Collections, Properties}
import scala.io.Source

object ProducerFromFile {
  // Set constants
  val BootstrapServer : String = "CHANGEME"
  val username: String = "CHANGEME"
  val password: String = "CHANGEME"
  //Use this for Windows
  //val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  def main(args: Array[String]): Unit = {
    // Create Kafka Producer
    val properties = getProperties(BootstrapServer)
    val admin = AdminClient.create(properties)
    val topics = admin.listTopics().names().get()
    if (!topics.contains("reviews")) {
      val newTopic = admin.createTopics(Collections.singletonList(new NewTopic("reviews", 3, 2.toShort)))
      newTopic.all().get()
      println("Created Reviews topic")
    }
    println(topics)
    writeFileToKafka(properties, "/Users/Kit/Code/amazon_reviews_us_Toys_v1_00_1000000.tsv", "reviews")
    //writeFileToKafka(properties, "/Users/Kit/Code/alice-in-wonderland.txt", "word-count")
  }

  def writeFileToKafka(properties: Properties, filename: String, topic: String) = {
    val producer = new KafkaProducer[String, String](properties)
    val file = new File(filename)
    val reader = new BufferedReader(new FileReader(file))
    try {
      var line = reader.readLine() // skip the header
      line = reader.readLine()
      while (line != null) {
        if (line.strip().nonEmpty) {
          val record = new ProducerRecord[String, String](topic, line)
          producer.send(record, new Callback() {
            override def onCompletion(recordMetadata: RecordMetadata, e: Exception): Unit = {
              if (e == null) {
                println(
                  s"""
                     |Sent Record: ${record.value()}
                     |Topic: ${recordMetadata.topic()}
                     |Partition: ${recordMetadata.partition()}
                     |Offset: ${recordMetadata.offset()}
                     |Timestamp: ${recordMetadata.timestamp()}
                   """.stripMargin)
              } else println("Error while producing", e)
            }
          })
        }
        line = reader.readLine()
      }
    } finally {
      reader.close()
      producer.close()
    }
  }

  def getProperties(bootstrapServer: String): Properties = {
    // Set Properties to be used for Kafka Producer
    val properties = new Properties
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
    properties.setProperty(ProducerConfig.ACKS_CONFIG, "1")
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)

    properties.put("security.protocol", "SASL_SSL")
    properties.put("sasl.mechanism", "SCRAM-SHA-512")
    properties.put("ssl.truststore.location", trustStore)
    properties.put("sasl.jaas.config", getScramAuthString(username, password))
    properties
  }
}
