package com.labs1904.hwe.consumers

import com.labs1904.hwe.consumers
import com.labs1904.hwe.util.Constants._
import com.labs1904.hwe.util.Util
import net.liftweb.json.DefaultFormats
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.KafkaProducer
import org.slf4j.LoggerFactory

import java.time.Duration
import java.util.Arrays

case class RawUser(id: String, username: String, name: String, email: String, birthday: String)

case class EnrichedUser(id: String, username: String, name: String, email: String, birthday: String, hweDeveloper : String)

object HweConsumer {
  private val logger = LoggerFactory.getLogger(getClass)

  val consumerTopic: String = "question-1"
  val producerTopic: String = "question-1-output"

  implicit val formats: DefaultFormats.type = DefaultFormats

  def main(args: Array[String]): Unit = {

    // Create the KafkaConsumer
    val consumerProperties = Util.getConsumerProperties(BOOTSTRAP_SERVER)
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](consumerProperties)

    // Create the KafkaProducer
    val producerProperties = Util.getProperties(BOOTSTRAP_SERVER)
    val producer = new KafkaProducer[String, String](producerProperties)

    // Subscribe to the topic
    consumer.subscribe(Arrays.asList(consumerTopic))

    while ( {
      true
    }) {
      // poll for new data
      val duration: Duration = Duration.ofMillis(5000)
      val records: ConsumerRecords[String, String] = consumer.poll(duration)

      records.forEach((record: ConsumerRecord[String, String]) => {
        // Retrieve the message from each record
        val message = record.value()
//        logger.info(s"Message Received: $message")
        // TODO: Add business logic here!
        val messageSplit = message.split("\\t")
        val rawUser = RawUser(messageSplit(0),messageSplit(1),messageSplit(2),messageSplit(3), messageSplit(4))
        val rawUserSplit = rawUser.toString.split(",")
        val enrichedUser = EnrichedUser(rawUserSplit(0), rawUserSplit(1), rawUserSplit(2), rawUserSplit(3), messageSplit(4), "Michael Allen")
        val enrichedUserAsString = enrichedUser.toString
        logger.info(s"Message Received: $enrichedUserAsString")
      })
    }
  }
}