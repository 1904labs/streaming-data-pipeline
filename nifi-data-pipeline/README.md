# Hours with Experts - Nifi Streaming Data Pipeline

A [Nifi](https://nifi.apache.org/) pipeline that ingests data
from [Kafka](https://kafka.apache.org/), supplements with data stored in [HBase](https://hbase.apache.org/book.html),
and saves the enriched output to [HDFS](https://hadoop.apache.org/docs/r1.2.1/hdfs_design.html). Done as part of
the [Hours with Experts](https://1904labs.com/our-culture/community/hours-with-experts/) course by 1904Labs.

## Project Instructions

This project will have you

1. Ingest data from a "reviews" Kafka topic.
2. Use the customer id to lookup the corresponding user data in HBase.
3. Split the MapRecord LookupUserData into seperate json fields
4. Merge flowfiles
5. Set Filename on HDFS
6. Put Flowfile on HDFS
7. Verify output

### Ingest data from a "reviews" Kafka topic.

Create a process to consume messages from kafka.

### Use the customer_id contained within the review message to lookup corresponding user data in HBase

Construct a HBase get request for every review message. The customer_id corresponds to a HBase rowkey.

### Split the MapRecord LookupUserData into seperate json fields

Nifi has a processor that transforms JSON flowfiles.

### Merge flowfiles

Consolidate flowfiles to minimize the number of files created in HDFS.

### Set Filename on HDFS

Give the give the flowfiles a filename before writing to HDFS.

### Put Flowfile on HDFS

Write the file to HDFS.

### Verify output

From the command line check the files written to HDFS.

```bash
hdfs dfs -ls /data/raw/
hdfs dfs -copyToLocal /data/raw/b46106fe-96df-41e1-a183-14ec27d99b28.snappy.parquet .
parquet-tools schema b46106fe-96df-41e1-a183-14ec27d99b28.snappy.parquet
parquet-tools head b46106fe-96df-41e1-a183-14ec27d99b28.snappy.parquet
```
