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
* Answer: A distributed (spread out between multiple servers) system (meaning software + servers (brokers), I think) which takes in a bunch of real time streaming data (events) from various sources, organizes it, and stores it temporarily (e.g. 1 day). Applications (consumers) who want to know about that data can connect to it (subscribe) and get it (consume it). Because Kafka is distributed, it can handle lots of data coming into it, and lots of consumers subscribing to it, without breaking (or rather, when something does break, there are backups so no data gets lost).  

#### Describe each of the following with an example of how they all fit together: 
 * Topic: group of partitions handling the same type of data
 * Producer: a process (aka application) that reads the updates (aka data?) (from where/what? IoT devices? etc.?) and writes (aka publishes) them to the queue (aka partition)
 * Consumer: a process (aka application) that consumes (aka reads?) the data from a certain group of the queues (aka topic) and displays it somewhere else (and/or...writes it somewhere else? And/or takes some action based on it? Etc.?)
 * Broker: aka server. It can hold one or more partitions.
 * Partition: aka queue. It's a slice of data that is all related to each other by having the same partition key (though sometimes a piece of data won't have a partition key, in which case it is assigned to a partition in various other ways). A partition is located on a server (broker).
 * Example: basketball game data. Multiple basketball games are being played at the same time. A producer writes the data from the games into various partitions. Each partition could be for a single game's data. If there are a lot of games, there could be a lot of partitions (say 100), and 5 partitions could be on a single server (broker), so you'd have 20 brokers. There could be other data about cars located on those same servers. The groups of partitions which hold car-related data would be the car "topic", and the groups of partitions which hold the basketball data would be the basketball "topic". If an application wanted to know about the basketball data so it could display it on a mobile app, then it would be a consumer of the basketball topic.  

#### Describe Kafka Producers and Consumers
* Answer: Producers are applications that write (publish) data to a topic for consumers to read (subscribe to). Producers manage where the data goes (like which partition it goes in, based on either the partition key or some other method if there is no partition key). There can be many producers writing to the same topic (and even the same partition? not sure). Consumers are described in detail below; the only additional thing I'll say here is it's nice that consumers are scalable without much effort from the developer. 

#### How are consumers and consumer groups different in Kafka? 
* Helpful resource: [Consumers](https://youtu.be/lAdG16KaHLs)
* Helpful resource: [Confluent Consumer Overview](https://youtu.be/Z9g4jMQwog0)
* Answer: 
  * a consumer group is...a group of consumers. 
  * I think a consumer is actually always "in a group", aka assigned a group id, but just sometimes that group has only one consumer in it.
  * A consumer group collectively always reads from all partitions in a topic. So if the consumer group has only one consumer and the topic has four partitions, that consumer reads from all four partitions. If a consumer group has two consumers, then probably one of them will read from two partitions, and the other will read from the other two partitions.
  * A consumer cannot read from any partition that some other consumer in the same group is already reading from. 
  * So suppose a consumer group has consumer 1, consumer 2, and consumer 3, and there are two partitions. If consumer 1 is reading from partition 1, and consumer 2 is reading from partition 2, then consumer 3 won't be allowed to read from anything and will sit idle.
  * Note: I think Kafka stores the offsets at a group level, not an individual level. So suppose c1 and c2 are in cg1, and c1 is reading from p1 and c2 is reading from p2. If c1 goes away (for whatever reason - goes offline, etc.) after reading offset 55, then Kafka stores something like "cg1 left off at 55". So then c2 could just take over, and Kafka knows c2 is in cg1, so c2 can just start reading at offset 56 in p1 (and continue reading p2 like always).
#### How are Kafka offsets different than partitions? 
* Answer: Kafka assigns a unique sequential number to each piece of data within a partition, and that number is called the "offset". The first piece of data in that partition will have offset 0, the second will have offset 1, etc. (so they are ordered by time, sequentially). The offsets are only unique within a partition - so both partition 0 and partition 1 will have offset 0, offset 1, offset 2, etc. The combination of partition + offset uniquely identifies a piece of data, I believe (aka, those two things together form the index of the piece of data?).

#### How is data assigned to a specific partition in Kafka? 
* Answer: Something about a piece of data is designated to be the "partition key". If a piece of data is "the score of the Knicks / Warriors game at halftime is 40-42" then the partition key could be "the Knicks / Warriors game". That piece of data goes into partition 0, in which every other piece of data with partition key "the Knicks / Warriors game" also goes. The data with partition key "the Bucks / Pistons game" goes into partition 1. Etc.

#### Describe immutability - Is data on a Kafka topic immutable? 
* Answer: 
  * From a producer's point of view, data can only be appended, not deleted or changed. 
  * From an administrative API point of view, data can be deleted - so if a producer appends Location: USA and then later appends Location: Canada, eventually the Location: USA will get deleted. I think this process is called...compaction? 
    * Also, if a producer appends Location: USA and then later just wants Location to be deleted entirely, not changed to Canada, then they would append Location: null and when the compaction process runs, it would delete location entirely (aka...add a "tombstone" I think?)
  * Also, all data is deleted automatically after the retention period (2 weeks by default, I think). 

#### How is data replicated across brokers in kafka? If you have a replication factor of 3 and 3 brokers, explain how data is spread across brokers
* Helpful resource [Brokers and Replication factors](https://youtu.be/ZOU7PJWZU9w)
* Answer: 
  * 1 topic, 1 partition: broker 1 (b1) has the "leader" partition (replica 1), and b2 and b3 each have a copy of that data, called "in sync replicas" (replicas 2 and 3)
  * 1 topic, 2 partitions: b1 has p1r1 and p2r2, b2 has p1r2 and p2r1, and b3 has p1r3 and p2r3
  * 1 topic, 3 partitions: b1 has p1r1, p2r2, p3r2; b2 has p1r2, p2r1, p3r3; b3 has p1r3, p2r3, p3r1
  * In general, if you have a replication factor of 3 and 3 brokers, then every piece of data will be on each of the three brokers, so that if one or even two of the brokers crash or go offline for some reason, you can still serve up the full dataset because each broker has a full copy of the data on it
  * Each partition has only one leader at a time; the rest of the replicas are followers, meaning they just copy data from the leader and nothing can be read from or written to them. When a leader goes away for some reason, there's some system for figuring out which follower will be promoted to the new leader

#### What was the most fascinating aspect of Kafka to you while learning? 
* somewhat similar to Cosmos DB (Azure's distributed NoSQL database), which I'm familiar with
* when a consumer is reading data, it (or Kafka?) is also writing [a small amount of] data to Kafka - the offset values that the consumer has read, so that it can leave, come back, and pick up where it left off - like a bookmark.
* seems pretty easy to use (from what people described, anyways...) and a lot of the hard work is done "under the hood"
* interesting that followers are not even allowed to be read from - seems like you might want that sometimes to allow for faster reads/scalability, even if the data isn't guaranteed to be 100% up to date?