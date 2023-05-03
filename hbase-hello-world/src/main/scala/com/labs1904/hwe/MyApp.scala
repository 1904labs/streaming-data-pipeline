package com.labs1904.hwe

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Get, Result, ResultScanner, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hbase.thirdparty.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer
import org.apache.logging.log4j.{LogManager, Logger}

import scala.collection.JavaConverters._

object MyApp {
  lazy val logger: Logger = LogManager.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("MyApp starting...")
    var connection: Connection = null
    try {
      val conf = HBaseConfiguration.create()
      conf.set("hbase.zookeeper.quorum", "hbase01.labs1904.com:2181")
      connection = ConnectionFactory.createConnection(conf)
      // Example code... change me
      val table = connection.getTable(TableName.valueOf("mallen:users"))
      val scan = new Scan()
      val scanner = table.getScanner(scan)
      val get = new Get(Bytes.toBytes("10000001"))
      val result = table.get(get)
//      logger.debug(scanner.forEach(_=>{
//       val count = 0
//        _.if(Some){}))
//      }
      logger.debug(result)
    } catch {
      case e: Exception => logger.error("Error in main", e)
    } finally {
      if (connection != null) connection.close()
    }
  }
}
