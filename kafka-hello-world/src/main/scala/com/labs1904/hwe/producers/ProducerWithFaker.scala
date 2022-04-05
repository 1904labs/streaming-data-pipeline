package com.labs1904.hwe.producers

import com.labs1904.hwe.util.Util.getScramAuthString
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer
import faker._
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

import java.util.Properties

case class User(name: String, username: String, email: String)

object ProducerWithFaker {
  implicit val formats: DefaultFormats.type = DefaultFormats
  val BootstrapServer : String = "CHANGEME"
  val Topic: String = "CHANGEME"
  val username: String = "CHANGEME"
  val password: String = "CHANGEME"
  //Use this for Windows
  val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  //val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  def main(args: Array[String]): Unit = {

    // Create the Kafka Producer
    val properties = getProperties(BootstrapServer)
    val producer = new KafkaProducer[String, String](properties)

    // create n fake records to send to topic
    val recordsToCreate = 10
    val range = (1 to recordsToCreate).toList

    range.map(id => {
      val key = id.toString

      // Use the faker library ( https://github.com/bitblitconsulting/scala-faker ) to generate Users
      // User, as a case class, is defined at the top of this file
      val name = Name.name
      val user = User(name, Internet.user_name(name), Internet.free_email(name))

      // Switching to use CSV instead of JSON
      //val jsonString = write(user)
      val csvString = key + "," + name.replace(",","") + "," + user.email.replace(",","")

      new ProducerRecord[String, String](Topic, key, csvString)
    }).foreach(record => {

      // send records to topic
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
    })

    producer.close()
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
