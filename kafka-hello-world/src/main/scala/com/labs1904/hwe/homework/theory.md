# Overview

Kafka has many moving pieces, but also has a ton of helpful resources to learn available online. In this homework, your
challenge is to write answers that make sense to you, and most importantly, **in your own words!**
Two of the best skills you can get from this class are to find answers to your questions using any means possible, and to
reword confusing descriptions in a way that makes sense to you. 

### Tips
* You don't need to write novels, just write enough that you feel like you've fully answered the question
* Use the helpful resources that we post next to the questions as a starting point, but carve your own path by searching on Google, YouTube, books in a library, etc to get answers!
* We're here if you need us. Reach out anytime if you want to ask deeper questions about a topic 
* This file is a markdown file. We don't expect you to do any fancy markdown, but you're welcome to format however you like

### Your Challenge
1. Create a new branch for your answers 
2. Complete all of the questions below by writing your answers under each question
3. Commit your changes and push to your forked repository

## Questions
#### What problem does Kafka help solve? Use a specific use case in your answer 
* Helpful resource: [Confluent Motivations and Use Cases](https://youtu.be/BsojaA1XnpM)
* Answer: The problem that Kafka helps solve is getting and processing data in real time so people/computers/etc. can react quickly to it if / when needed. One use case is identifying potential credit card fraud - if the credit card provider gets and processes data that seems like it could be a fraudulent transaction, it notifies the credit card holder very quickly so they can take appropriate action. 

#### What is Kafka?
* Helpful resource: [Kafka in 6 minutes](https://youtu.be/Ch5VhJzaoaI) 
* Answer: A distributed (spread out between multiple servers) system (meaning software + servers (brokers), data stores, etc.) which takes in a bunch of real time streaming data (events) from various sources, organizes it, and stores it temporarily (e.g. 1 day). People / computers / etc. (consumers) who want to know about that data can connect to it (subscribe) and get it (consume it). Because Kafka is distributed, it can handle lots of data coming into it, and lots of consumers subscribing to it, without breaking (or rather, when something does break, there are backups so no data gets lost).  

#### Describe each of the following with an example of how they all fit together: 
 * Topic: group of partitions handling the same type of data
 * Producer: a process (?) that reads the updates (aka data?) (from where/what?) and writes them to the queue (aka partition)
 * Consumer: a process (?) that consumes (aka reads?) the data from the queue and displays it somewhere else (and/or...writes it somewhere else? And/or takes some action based on it? Etc.?)
 * Broker: aka server. It can hold one or more partitions.
 * Partition: aka queue. It's a slice of data that is all related to each other by having the same partition key. A partition is located on a server (broker).
 * Example: basketball game data. Multiple basketball games are being played at the same time. A producer writes the data from the games into various partitions. Each partition could be for a single game's data. If there are a lot of games, there could be a lot of partitions (say 100), and 5 partitions could be on a single server (broker), so you'd have 20 brokers. There could be other data about cars located on those same servers. The groups of partitions which hold car-related data would be the car "topic", and the groups of partitions which hold the basketball data would be the basketball "topic". If an application wanted to know about the basketball data so it could display it on a mobile app, then it would be a consumer of the basketball topic.  

#### Describe Kafka Producers and Consumers
* Answer: 

#### How are consumers and consumer groups different in Kafka? 
* Helpful resource: [Consumers](https://youtu.be/lAdG16KaHLs)
* Helpful resource: [Confluent Consumer Overview](https://youtu.be/Z9g4jMQwog0) **[START HERE]**
* Answer: 
  * a consumer group is...a group of consumers. 
  * A consumer can read from any number of partitions. 
  * However, if a consumer is in a consumer group, it cannot read from any partition that some other consumer in the same group is already reading from. 
  * So suppose a consumer group has consumer 1, consumer 2, and consumer 3, and there are four partitions. If consumer 1 is reading from all four partitions, then consuemrs 2 and 3 can't read from anything.
  * Note: I think Kafka stores the offsets a group level, not an individual level. So suppose c1 and c2 are in cg1, and c1 is reading from p1 and c2 is reading from p2. If c1 goes away (for whatever reason) after reading offset 55, then Kafka stores something like "cg1 left off at 55". So then c2 could just take over, and Kafka knows c2 is in cg1, so c2 can just start reading at offset 56 in p1 (and continue reading p2 like always).
#### How are Kafka offsets different than partitions? 
* Answer: Kafka assigns a unique sequential number to each piece of data within a partition, and that number is called the "offset". It means basically the same thing as index. The first piece of data in that partition will have offset 0, the second will have offset 1, etc. (so they are ordered by time, sequentially). The offsets are only unique within a partition - so both partition 0 and partition 1 will have offset 0, offset 1, offset 2, etc. The combination of partition + offset uniquely identifies a piece of data, I believe. 

#### How is data assigned to a specific partition in Kafka? 
* Answer: Something about a piece of data is designated to be the "partition key". If a piece of data is "the score of the Knicks / Warriors game at halftime is 40-42" then the partition key could be "the Knicks / Warriors game". That piece of data goes into partition 0, in which every other piece of data with partition key "the Knicks / Warriors game" also goes. The data with partition key "the Bucks / Pistons game" goes into partition 1. Etc.

#### Describe immutability - Is data on a Kafka topic immutable? 
* Answer: 

#### How is data replicated across brokers in kafka? If you have a replication factor of 3 and 3 brokers, explain how data is spread across brokers
* Helpful resource [Brokers and Replication factors](https://youtu.be/ZOU7PJWZU9w)
* Answer: 

#### What was the most fascinating aspect of Kafka to you while learning? 
* similar to Cosmos DB (Azure's distributed NoSQL database)
* when a consumer is reading data, it (or Kafka?) is also writing [a small amount of] data to Kafka - the offset values that the consumer has read, so that it can leave, come back, and pick up where it left off - like a bookmark.