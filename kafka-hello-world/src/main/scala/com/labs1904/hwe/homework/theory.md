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

Answer: Kafka solves problems created by entities and industries that (1) create an excessive amount of data (or track an excessive amount of 'events') and (2) need quick access to their data for practical or analytical purposes.

For example, an ecommerce website would want access to a vast number of analytical viewpoints: how much a product sells, how much a product sits in someone's cart but gets removed, how many people make carts yet don't confirm sales, on and on. Kafka allows for this information so that the website is able to react in real time to increase sales.

#### What is Kafka?
* Helpful resource: [Kafka in 6 minutes](https://youtu.be/Ch5VhJzaoaI) 

Answer: Kafka is an event streaming platform. It can, in real time, take in an almost unlimited amount of data points or events, and stream them in ways in which consumers are able (via other apps) to make quick sense of the information. 

#### Describe each of the following with an example of how they all fit together: 
 * Topic
 * Producer
 * Consumer 
 * Broker
 * Partition

Topic: Topics are the streams of data to which messages are stored. They are written by producers onto partitions that are being managed by the broker. These streams contain the data the various consumers want.

Producer: An process that writes events to a queue. This is logging the entity that is creating the event stream.

Consumer: This is the entity that wants the data logged by the producer. They consume the events and display them on various channels.

Broker: A broker is the server running Kafka. It stores the data in various partitions as an append-only log. Kafka works by clustering brokers together, each holding backups of partitions from other brokers.

Partitions: These are like sub-topics, and each is a logfile with new message appended on the end. You can specify how many replicates you want for each partition upon creation.

#### Describe Kafka Producers and Consumers

Some entity or group is creating data. Another entity or group wants that data.

With Kafka's architecture, a producer is needed to capture that data in real time and a consumer is needed to read that data in real time.

#### How are consumers and consumer groups different in Kafka? 
* Helpful resource: [Consumers](https://youtu.be/lAdG16KaHLs)
* Helpful resource: [Confluent Consumer Overview](https://youtu.be/Z9g4jMQwog0)

Consumers are only interested in reading data from a topic. They specify what topics and brokers, but don't care about the rest.

Consumer groups are clusters of consumers, each reading specific partitions that the other consumers don't read. Together they share information and reduce the load for each consumer.

#### How are Kafka offsets different than partitions? 

Partitions are containers for offsets. When new data gets published to the partition, it creates a new offset. Kafka knows from which offset a consumer last read.

#### How is data assigned to a specific partition in Kafka? 

Using message keys

#### Describe immutability - Is data on a Kafka topic immutable? 

Yes, data is what it is.

#### How is data replicated across brokers in kafka? If you have a replication factor of 3 and 3 brokers, explain how data is spread across brokers
* Helpful resource [Brokers and Replication factors](https://youtu.be/ZOU7PJWZU9w)

When a partition gets created, it will then create a replica of that partition at another broker. You can not have a higher replication factor than there are brokers, as each broker can only have one copy of a partition.

#### What was the most fascinating aspect of Kafka to you while learning? 

All of it. I had no idea how any of this worked, and it's all fascinating.