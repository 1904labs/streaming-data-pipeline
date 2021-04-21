# Hours with Experts - Streaming Data Pipeline

A spark-streaming application that ingests data from Kafka, supplements with data stored in HBase, and saves the
enriched output to HDFS. Done as part of the Hours with Experts course.

# Getting Started

To start, clone this repo to your computer:

```
git clone https://github.com/1904labs/streaming-data-pipeline.git
```

1. Open the pom.xml in Intellij
2. Select “Open as Project”

Give IntelliJ a few minutes to download the project's dependencies. You'll see the progress bar in the bottom right
loading.

## Project Instructions

This project will have you

1. Ingest data from a "reviews" Kafka topic.
2. Parse the comma separated values into a Review scala case class
3. Extract the userId from the Review object.
4. Use the userId to lookup the corresponding user data in HBase.
5. Join the review data with the user data.
6. Save this combined result in hdfs

### Slides Accompanying the instructions

[HWE 2021 Project Week](https://docs.google.com/presentation/d/1VYreCRMDD3F6a9Xn2pP13mYxUZN8TL4wZHpxSQaysz0/edit?usp=sharing "Week 6 Slides")

### Ingest data from a "reviews" Kafka topic.

Adjust the Spark application to read from the "reviews topic". Make sure to change the bootstrap server URL to the 1904
provided cluster. Verify the output using the console sink provided.

### Parse each message from the "reviews" topic into a Scala case class.

A small sample of messages can be found here.

### Use the userId from the review message to lookup

HBase get example.

Tip:
Open up a connection per partition, instead of per row:
Link to google slide here.

### Join the review data with teh user data into a Scala case class.

Verify the joined output using the console sink provided.

### Save this combined result in hdfs.

Open up `StreamingPipeline.scala` from src/main/scala/com/labs1904/spark.

### Stretch: Filter out junk data.

A separate topic exists called reviews-and-junk, which mostly contains reviews in the same format as above. However, it
also contains junk data. Change your application to consume from this reviews-and-junk. Filter out junk messages so that
they don't break your data pipeline. Tip:
Connecting a Kafka console consumer to the reviews-and-junk topic can help to see what junk data is giving your
application trouble.