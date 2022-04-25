# Hours with Experts - Streaming Data Pipeline

A [Spark streaming](https://spark.apache.org/docs/latest/) application that ingests data
from [Kafka](https://kafka.apache.org/), supplements with data stored in [HBase](https://hbase.apache.org/book.html),
and saves the enriched output to [HDFS](https://hadoop.apache.org/docs/r1.2.1/hdfs_design.html). Done as part of
the [Hours with Experts](https://1904labs.com/our-culture/community/hours-with-experts/) course by 1904Labs

## Project Instructions

This project will have you

1. Ingest data from a "reviews" Kafka topic.
2. Parse the values into a Review scala case class
3. Use the customer id to lookup the corresponding user data in HBase.
4. Join the review data with the user data.
5. Save this combined result in hdfs
   1. Setup a Hive Table that points to the enriched result stored in HDFS

### Ingest data from a "reviews" Kafka topic.

Adjust the Spark application to read from the "reviews" topic. Make sure to change the bootstrap server URL to the 1904
provided cluster. Verify the output using the console sink provided.

### Parse each message from the "reviews" topic into a Scala case class.

- In the StreamingPipeline.scala file, define a Scala case class above the object definition. 
- A [sample](src/main/resources/reviews.tsv) of reviews, with column names, is located within the resources directory.

```
marketplace	customer_id	review_id	product_id	product_parent	product_title	product_category	star_rating	helpful_votes	total_votes	vine	verified_purchase	review_headline	review_body	review_date
US	18778586	RDIJS7QYB6XNR	B00EDBY7X8	122952789	Monopoly Junior Board Game	Toys	5	0	0	N	Y	Five Stars	Excellent!!!	2015-08-31
US	24769659	R36ED1U38IELG8	B00D7JFOPC	952062646	56 Pieces of Wooden Train Track Compatible with All Major Train Brands	Toys	5	0	0	N	Y	Good quality track at excellent price	Great quality wooden track (better than some others we have tried). Perfect match to the various vintages of Thomas track that we already have. There is enough track here to have fun and get creative incorporating your key pieces with track splits, loops and bends.	2015-08-31
US	44331596	R1UE3RPRGCOLD	B002LHA74O	818126353	Super Jumbo Playing Cards by S&S Worldwide	Toys	2	1	1	N	Y	Two Stars	Cards are not as big as pictured.	2015-08-31
```

### Use the customer_id contained within the review message to lookup corresponding user data in HBase.

Construct a HBase get request for every review message. The customer_id corresponds to a HBase rowkey.

**Tip**:
Open up a connection per partition, instead of per row

### Join the review data with the user data into a Scala case class.

Create a new case class that holds information for the review data and its corresponding user data. Verify your joined
data by running the application and outputting via the console sink.

### Save this combined result in hdfs.

Adjust the write stream configuration to write to hdfs rather than outputting to the console.

```
val query = result.writeStream
  .outputMode(OutputMode.Append())
  .format("json")
  .option("path", s"/user/${hdfsUsername}/reviews_json")
  .option("checkpointLocation", s"/user/${hdfsUsername}/reviews_checkpoint")
  .trigger(Trigger.ProcessingTime("5 seconds"))
  .start()
```

### Setup a Hive table that points to the enriched result stored in hdfs.

- Create an external table
- Write and run a query to verify that the data is successfully stored ( e.g. select all usernames who gave reviews a
  rating of 4 or greater )

### Stretch: Filter out junk data.

A separate topic exists called reviews-and-junk, which mostly contains reviews in the same format as above as well as
junk data. While consuming from this new topic, filter out junk messages they don't break your data pipeline.

**Tip**:

- Connecting a Kafka console consumer to the reviews-and-junk topic can help to see what junk data is giving your
  application trouble.
  