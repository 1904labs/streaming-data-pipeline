package com.labs1904.spark

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.scalatest.FunSuite


class StreamingPipelineTests extends FunSuite with DataFrameSuiteBase {
  test("files were written to our user directory") {
    val config = new Configuration()
    config.set("dfs.client.use.datanode.hostname", "true")
    config.set("fs.defaultFS", StreamingPipeline.hdfsUrl)
    val filesystem = FileSystem.get(config)
    val fileStatuses = filesystem.listStatus(new Path(s"/user/${StreamingPipeline.hdfsUsername}/reviews_json/"))
    fileStatuses.foreach(println)
    assert(fileStatuses.length > 0)
  }
}
