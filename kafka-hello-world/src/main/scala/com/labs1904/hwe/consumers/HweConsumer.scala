package com.labs1904.hwe.consumers

import com.labs1904.hwe.producers.SimpleProducer
import com.labs1904.hwe.util.Util
import com.labs1904.hwe.util.Util.{getScramAuthString, mapNumberToWord}
import net.liftweb.json.DefaultFormats
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.{Arrays, Properties, UUID}

case class EnrichedUser(id: Int, name: String, email: String, numberAsWord: String, hweDeveloper: String)

object HweConsumer {
  val BootstrapServer : String = "b-2-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196,b-1-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196,b-3-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196"
  val consumerTopic: String = "question-1"
  val producerTopic: String = "question-1-output"
  val username: String = "hwe"
  val password: String = "1904labs"
  //Use this for Windows
//  val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

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

//         println(s"Message Received: $message")
        // TODO: Add business logic here!
        val messageSplit = message.split(",")
        val id = messageSplit(0).toInt
        val name = messageSplit(1)
        val email = messageSplit(2)
        val enrichedUser = EnrichedUser(id = id, name = name, email = email, numberAsWord = mapNumberToWord(id), hweDeveloper = "Ben Malburg")
        println(enrichedUser)

        val enrichedUserString = enrichedUser.toString.mkString("")
        val producerRecord = new ProducerRecord[String, String](producerTopic, enrichedUserString)
//        println(enrichedUserString)
        producer.send(producerRecord)

      })
    }
  }
}