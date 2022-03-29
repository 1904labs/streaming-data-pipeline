package com.labs1904.hwe.consumers

import com.labs1904.hwe.util.Util.getScramAuthString
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util
import java.util.{Properties, UUID}

object JSONConsumer {

  val BootstrapServer : String = "CHANGEME"
  val Topic: String = "CHANGEME"
  val username: String = "CHANGEME"
  val password: String = "CHANGEME"
  //Use this for Windows
  val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  //val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  implicit val formats: DefaultFormats.type = DefaultFormats

  def main(args: Array[String]): Unit = {
    // Create the KafkaConsumer
    val properties = getProperties(BootstrapServer)
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](properties)

    // Subscribe to the topic
    consumer.subscribe(util.Arrays.asList(Topic))

    while ( {
      true
    }) {
      // poll for new data
      val duration: Duration = Duration.ofMillis(100)
      val records: ConsumerRecords[String, String] = consumer.poll(duration)

      records.forEach((record: ConsumerRecord[String, String]) => {
        /*
          // DEBUG Info
          println(s"Key: ${record.key}. Value: ${record.value}")
          println(s"Partition: ${record.partition}, Offset ${record.offset}")
        */

        /*
          1. Retrieve and print the message from each record
          2. Define a case class,  at the top of this file, that matches the message structure ( https://docs.scala-lang.org/tour/case-classes.html )
          3. Parse the JSON string into a scala case class ( https://alvinalexander.com/scala/simple-scala-lift-json-example-lift-framework/ )
         */

        val message = record.value()
        println(s"Message Received: $message")
      })
    }
  }

  def getProperties(bootstrapServer: String): Properties = {
    // Set Properties to be used for Kafka Consumer
    val properties = new Properties
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString)
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

    properties.put("security.protocol", "SASL_SSL")
    properties.put("sasl.mechanism", "SCRAM-SHA-512")
    properties.put("ssl.truststore.location", trustStore)
    properties.put("sasl.jaas.config", getScramAuthString(username, password))

    properties
  }
}
