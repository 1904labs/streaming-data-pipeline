package com.labs1904.hwe.util

import java.nio.file.Paths

object Constants {

  private val ENVIRONMENT_VAR_NAME = "KAFKA_HELLO_WORLD"

  private var connectionVals: Map[String, String] = null;
  private val NAME_VALUE_PAIR_SPLIT = '|'
  private val NAME_VALUE_SPLIT = '='

  private def getConnectionValues(valueName: String): String = {

    if (connectionVals == null) {
      val envVar: Option[String] = sys.env.get(ENVIRONMENT_VAR_NAME)
      if (envVar.isEmpty) {
        // Note that if you get this even after creating the environment variable you might need
        // to restart IntelliJ to get the environment variable to be loaded into the process.
        throw new IllegalStateException(s"Environment variable named '$ENVIRONMENT_VAR_NAME'" +
          s" was not found. Please seed the environment variable name '$ENVIRONMENT_VAR_NAME' with" +
          " a string of the following format:\r\n" +
          "  VAL_NAME_1=\"val1\"|VAL_NAME_2=\"val2\"|...VAL_NAME_N=\"valn\"  \r\n\r\n" +
          "Note you may need to restart the IDE after setting the environment variable."
        );
      }
      else {
        // The environment variable needs to be in this form:
        //   VAL_NAME_1="val1"|VAL_NAME_2="val2"|...VAL_NAME_N="valn"
        // So this code first splits the string vals by '|', then
        // finds string tokens split by '='.  Then makes those values
        // split to map key and values.
        connectionVals = envVar.get.split(NAME_VALUE_PAIR_SPLIT).
          filter(nameValPair => nameValPair.split(NAME_VALUE_SPLIT).length == 2).
          map(nameValPair => nameValPair.split(NAME_VALUE_SPLIT)).
          map(nameValPairTuple => (nameValPairTuple(0), nameValPairTuple(1).replaceAll("\"", "").trim)).toMap
      }
    }
    val value: Option[String] = connectionVals.get(valueName)
    value.getOrElse(throw new IllegalStateException(s"Environment variable named '$ENVIRONMENT_VAR_NAME'" +
      s" does not contain value for key '$valueName'. " +
      s"Please correct the environment variable. " +
      "Note you may need to restart the IDE after correcting the environment variable."
    ))
  }

  def BOOTSTRAP_SERVER: String = {
    val BOOTSTRAP_SERVER_NAME = "BOOTSTRAP_SERVER";
    getConnectionValues(BOOTSTRAP_SERVER_NAME)
  }

  val DEFAULT_TOPIC: String = {
    val DEFAULT_TOPIC_NAME = "DEFAULT_TOPIC";
    getConnectionValues(DEFAULT_TOPIC_NAME)
  }

  val USERNAME: String = {
    val USERNAME_NAME = "USERNAME";
    getConnectionValues(USERNAME_NAME)
  }

  val PASSWORD: String = {
    val PASSWORD_NAME = "PASSWORD";
    getConnectionValues(PASSWORD_NAME)
  }

  //Use this for Windows
  val TRUST_STORE: String = {
    val url = getClass.getResource("/kafka.client.truststore.jks")
    if (url == null) throw new Exception("Unable to find kafka.client.truststore.jks in resources, is the project opened correctly?")
    else Paths.get(url.toURI()).toString
  }
}
