package com.labs1904.hwe.consumers

import com.labs1904.hwe.util.Util.getScramAuthString
import net.liftweb.json.DefaultFormats
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.{Arrays, Properties, UUID}

object SimpleConsumer {
  val BootstrapServer : String = "b-2-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196,b-1-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196,b-3-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196"
  val Topic: String = "question-1"
  val username: String = "hwe"
  val password: String = "1904labs"
  //Use this for Windows
//  val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  implicit val formats: DefaultFormats.type = DefaultFormats

  def main(args: Array[String]): Unit = {

    // Create the KafkaConsumer
    val properties = getProperties(BootstrapServer)
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](properties)

    // Subscribe to the topic
    consumer.subscribe(Arrays.asList(Topic))

    while ( {
      true
    }) {
      // poll for new data
      val duration: Duration = Duration.ofMillis(100)
      val records: ConsumerRecords[String, String] = consumer.poll(duration)

      records.forEach((record: ConsumerRecord[String, String]) => {
        // Retrieve the message from each record
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
