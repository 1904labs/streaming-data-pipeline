package com.labs1904.hwe.util

import java.nio.file.Paths

object Constants {
  // TODO: this is configured to use kafka running locally, change it to your cluster
<<<<<<< HEAD
  val BOOTSTRAP_SERVER : String = "CHANGE ME"
  val DEFAULT_TOPIC: String = "hwe-kafka-connection-test"
=======

  val BOOTSTRAP_SERVER : String = "CHANGEME"
  val DEFAULT_TOPIC: String = "connection-test"
>>>>>>> f2c316f6e86b9856b1b2647520a25dd06efd64bc
  val USERNAME: String = "1904labs"
  val PASSWORD: String = "1904labs"

  //Use this for Windows
  val TRUST_STORE: String = {
    val url = getClass.getResource("/kafka.client.truststore.jks")
    if (url == null) throw new Exception("Unable to find kafka.client.truststore.jks in resources, is the project opened correctly?")
    else Paths.get(url.toURI()).toString
  }
}
