package com.labs1904.hwe.consumers

import com.labs1904.hwe.producers.SimpleProducer
import com.labs1904.hwe.util.Util
import com.labs1904.hwe.util.Util.getScramAuthString
import net.liftweb.json.DefaultFormats
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.{Arrays, Properties, UUID}

case class RawUser(id: Int, name: String, email: String)
case class EnrichedUser(id: Int, name: String, email: String, idAsWord: String, hweDeveloper: String)

object HweConsumer {
  val BootstrapServer : String = "CHANGEME"
  val consumerTopic: String = "CHANGEME"
  val producerTopic: String = "CHANGEME"
  val username: String = "CHANGEME"
  val password: String = "CHANGEME"
  //Use this for Windows
  val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  //val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  implicit val formats: DefaultFormats.type = DefaultFormats

  def main(args: Array[String]): Unit = {

    // Create the KafkaConsumer
    val consumerProperties = SimpleConsumer.getProperties(BootstrapServer)
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](consumerProperties)

    // Create the KafkaProducer
    val producerProperties = SimpleProducer.getProperties(BootstrapServer)
    val producer = new KafkaProducer[String, String](producerProperties)

    // Subscribe to the topic
    consumer.subscribe(Arrays.asList(consumerTopic))

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
        val split = message.split(",")
        val rawUser = RawUser(split(0).toInt, split(1), split(2))
        println(s"Converted to raw case class $rawUser")
        val enrichedUser = EnrichedUser(rawUser.id, rawUser.name, rawUser.email, Util.numberToWordMap(rawUser.id), "Tim")
        println(s"Converted to enriched case class $enrichedUser")
        val enrichedString = enrichedUser.name + "," + enrichedUser.name + "," + enrichedUser.email + "," + enrichedUser.idAsWord + "," + enrichedUser.hweDeveloper
        println(s"Converted to enrichedString $enrichedString")
        val producerRecord = new ProducerRecord[String, String](producerTopic, enrichedString)
        producer.send(producerRecord)

      })
    }
  }
}
