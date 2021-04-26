# Hours with Experts - Streaming Data Pipeline

A [Spark streaming](https://spark.apache.org/docs/latest/) application that ingests data
from [Kafka](https://kafka.apache.org/), supplements with data stored in [HBase](https://hbase.apache.org/book.html),
and saves the enriched output to [HDFS](https://hadoop.apache.org/docs/r1.2.1/hdfs_design.html). Done as part of
the [Hours with Experts](https://1904labs.com/our-culture/community/hours-with-experts/) course by 1904Labs

## Getting Started

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
7. Setup a Hive Table that points to the enriched result stored in HDFS

### Slides Accompanying the instructions

[HWE 2021 Project Week](https://docs.google.com/presentation/d/1VYreCRMDD3F6a9Xn2pP13mYxUZN8TL4wZHpxSQaysz0/edit?usp=sharing "Week 6 Slides")

### Ingest data from a "reviews" Kafka topic.

Adjust the Spark application to read from the "reviews" topic. Make sure to change the bootstrap server URL to the 1904
provided cluster. Verify the output using the console sink provided.

### Parse each message from the "reviews" topic into a Scala case class.

- [Scala case class](https://docs.google.com/presentation/d/1cdcJQFleLNBTCyjc-Ah9pdUM2cAp3NcedRinknjdUjo/edit#slide=id.gca151140f3_0_139)
- A [sample](src/main/resources/reviews.csv) of reviews, with column names, is located within the resources directory.

```
marketplace,customer_id,review_id,product_id,product_parent,product_title,product_category,star_rating,helpful_votes,total_votes,vine,verified_purchase,review_headline,review_body,review_date
US,18778586,RDIJS7QYB6XNR,B00EDBY7X8,122952789,Monopoly Junior Board Game,Toys,5,0,0,N,Y,Five Stars,Excellent!!!,2015-08-31
US,24769659,R36ED1U38IELG8,B00D7JFOPC,952062646,56 Pieces of Wooden Train Track Compatible with All Major Train Brands,Toys,5,Good quality track at excellent price,Great quality wooden track (better than some others we have tried). Perfect match to the various vintages of Thomas track that we already have. There is enough track here to have fun and get creative incorporating your key pieces with track splits, loops and bends.,2015-08-31
US,44331596,R1UE3RPRGCOLD,B002LHA74O,818126353,Super Jumbo Playing Cards by S&S Worldwide,Toys,2,1,1,N,Two Stars,Cards are not as big as pictured.,2015-08-31
```

### Use the customer_id contained within the review message to lookup corresponding user data in HBase.

Construct
an [HBase get request](https://docs.google.com/presentation/d/1wPMeesO5DvceGm0BXhgaxFq6DUbtKPkg600lqHnlar0/edit#slide=id.gd2e67ee890_2_6)
for every review message. The customer_id corresponds to a HBase rowkey.

**Tip**:
[Open up a connection per partition, instead of per row](https://docs.google.com/presentation/d/1VYreCRMDD3F6a9Xn2pP13mYxUZN8TL4wZHpxSQaysz0/edit#slide=id.gcd61ac9710_0_10)

### Join the review data with the user data into a Scala case class.

Create a new case class that holds information for the review data and its corresponding user data. Verify your joined
data by running the application and outputting via the console sink.

### Save this combined result in hdfs.

Adjust the write stream configuration to write to hdfs rather than outputting to the console.
[Example and config options ](https://docs.google.com/presentation/d/1VYreCRMDD3F6a9Xn2pP13mYxUZN8TL4wZHpxSQaysz0/edit#slide=id.gcd61ac9710_0_31)

### Setup a Hive table that points to the enriched result stored in hdfs.

- [Create an external table](https://docs.google.com/presentation/d/1vstFy3dXS0tV88yYntIsvfVg8J0mvdCe9DJ3yIRb4c4/edit#slide=id.g829663288b_0_318)
- Write and run a query to verify that the data is successfully stored ( e.g. select all usernames who gave reviews a
  rating of 4 or greater )

### Stretch: Filter out junk data.

A separate topic exists called reviews-and-junk, which mostly contains reviews in the same format as above as well as
junk data. While consuming from this new topic, filter out junk messages they don't break your data pipeline.

**Tip**:

- Connecting a Kafka console consumer to the reviews-and-junk topic can help to see what junk data is giving your
  application trouble.
  