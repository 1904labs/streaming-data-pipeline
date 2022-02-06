package com.labs1904.hwe.producers

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties

object SimpleProducer {
  // Set constants
  val BootstrapServer = "35.239.241.212:9092,35.239.230.132:9092,34.69.66.216:9092"
  val Topic: String = "change-me"

  def main(args: Array[String]): Unit = {
    // Create Kafka Producer
    val properties = getProperties(BootstrapServer)
    val producer = new KafkaProducer[String, String](properties)
    val messageToSend = "Change Me"

    val record = new ProducerRecord[String, String](Topic, messageToSend)

    producer.send(record)

    producer.close()
  }

  def getProperties(bootstrapServer: String): Properties = {
    // Set Properties to be used for Kafka Producer
    val properties = new Properties
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
    properties.setProperty(ProducerConfig.ACKS_CONFIG, "1")
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    properties
  }
}
