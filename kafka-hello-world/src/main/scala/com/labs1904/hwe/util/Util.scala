package com.labs1904.hwe.util

object Util {
  def getScramAuthString(username: String, password: String) = {
   s"""org.apache.kafka.common.security.scram.ScramLoginModule required
   username=\"$username\"
   password=\"$password\";"""
  }
}
