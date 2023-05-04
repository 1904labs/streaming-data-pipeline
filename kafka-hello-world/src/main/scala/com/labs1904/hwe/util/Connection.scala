package com.labs1904.hwe.util

import java.nio.file.Paths


/*
The connection class requires creating 4 environment variables before running the app and possibly before starting IntelliJ (see further explanation below).  Please fill the following 4 environment variables with values provided by the HWE admins:

- HWE_KAFKA_BOOTSTRAP_SERVER
- HWE_KAFKA_DEFAULT_TOPIC
- HWE_KAFKA_PASSWORD
- HWE_KAFKA_USERNAME

If the environment variables are not present, an exception will be thrown informing developers
what environment variable is missing.

Note that some environments like Windows need to have IntelliJ restarted before the environment variables will be present in the environment (I haven't tested Linux and Mac).
 */
object Connection {

  /*
  HWE_KAFKA --> Hours With Experts - Kafka
   */
  private val CONXN_VALNAME_PREFIX = "HWE_KAFKA_"

  private def getConnectionValue(envVarName: String): String = {
      val envVar: Option[String] = sys.env.get(envVarName)
      if (envVar.isEmpty)
        {
          // Note that if you get this even after creating the environment variable you might need
          // to restart IntelliJ to get the environment variable to be loaded into the process.
          throw new IllegalStateException(s"Environment variable named '$envVarName'" +
            s" was not found. Please create the environment variable named '$envVarName' with" +
            " the values given by the HWE admins.\r\n" +
            "Note you may need to restart the IDE after setting the environment variable.");
        }
       envVar.get
  }

  /** Connection string to the Kafka bootstrap server.
   *
   * @throws IllegalStateException if the environment variable is not found.
   */
  @throws(classOf[IllegalStateException])
  val BOOTSTRAP_SERVER: String = {
    val BOOTSTRAP_SERVER_NAME = "BOOTSTRAP_SERVER";
    getConnectionValue(CONXN_VALNAME_PREFIX + BOOTSTRAP_SERVER_NAME)
  }

  /** The default Kafka topic.
   *
   * @throws IllegalStateException if the environment variable is not found.
   */
  @throws(classOf[IllegalStateException])
  val DEFAULT_TOPIC: String = {
    val DEFAULT_TOPIC_NAME = "DEFAULT_TOPIC";
    getConnectionValue(CONXN_VALNAME_PREFIX + DEFAULT_TOPIC_NAME)
  }

  /** The Kafka connection username.
   *
   * @throws IllegalStateException if the environment variable is not found.
   */
  @throws(classOf[IllegalStateException])
  val USERNAME: String = {
    val USERNAME_NAME = "USERNAME";
    getConnectionValue(CONXN_VALNAME_PREFIX + USERNAME_NAME)
  }

  /** The Kafka connection password.
   *
   * @throws IllegalStateException if the environment variable is not found.
   */
  @throws(classOf[IllegalStateException])
  val PASSWORD: String = {
    val PASSWORD_NAME = "PASSWORD";
    getConnectionValue(CONXN_VALNAME_PREFIX + PASSWORD_NAME)
  }

  //Use this for Windows
  val TRUST_STORE: String = {
    val url = getClass.getResource("/kafka.client.truststore.jks")
    if (url == null) throw new Exception("Unable to find kafka.client.truststore.jks in resources, is the project opened correctly?")
    else Paths.get(url.toURI()).toString
  }

}
