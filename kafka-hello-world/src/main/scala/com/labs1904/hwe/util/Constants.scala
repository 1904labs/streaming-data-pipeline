package com.labs1904.hwe.util

object Constants {
  // TODO: this is configured to use kafka running locally, change it to your cluster
  val BOOTSTRAP_SERVER : String = "CHANGEME"
  val DEFAULT_TOPIC: String = "hwe-kafka-connection-test"
  val USERNAME: String = "CHANGEME"
  val PASSWORD: String = "CHANGEME"
  //Use this for Windows
  val TRUST_STORE: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  //val TRUST_STORE: String = "src/main/resources/kafka.client.truststore.jks"
}
