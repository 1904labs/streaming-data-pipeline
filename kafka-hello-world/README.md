# Kafka Hello World

A maven project for interacting with Kafka.

## Homework Instructions
Your work on the homework is to use any resources you can find to answer all the questions asked in theory.md and KafkaHomework.scala.
Here is what you need to complete: 

* Start by completing your answers to the questions in src/main/com.labs1904.hwe/homework/theory.md. There are further instructions on theory.md that give tips and helpful resources to guide you in the right direction. You are welcome to use any resources you can find (youtube, articles, books, your teachers, etc)
* Complete the TODOs in KafkaHomework.scala in the same directory as theory.md
* **Important Note** - Do not commit the bootstrap servers to github. You are welcome to commit and push your work to github, but make sure to change them back to "change-me" before committing and pushing to github. 
## Studio Instructions



### The Lab


#### Run connection test to make sure you have access

In com.labs1904.hwe navigate to ConnectionTest and fill out the strings with the correct values that are given to you in class. 
Run the class and make sure you have an output that says Message Received: <some message> 

#### Consume from "question-1" and print to console

A topic exists called "question-1" with tab delimited strings as its messages. Navigate to HweConsumer and write code to:

- Consume from the topic
- Print out the strings to understand their structure.

#### Consume from "question-1" and populate a case class

Modify your solution to the problem above by adding the following functionality:
- Create a case class named `RawUser` that matches the data's structure.
- Split the TSV by tabs /t, and populate an instance of the scala case class that you created.

#### Consume from "question-1", populate a case class, and enrich the data from a map

Modify your solution to the problem above by adding the following functionality:
- Create a new case class named `EnrichedUser` that adds 2 new columns:
   * numberAsWord - convert the first field (which is numeric) into a string using the `Util.mapNumberToWord` function
   * hweDeveloper - hard-code your name here. (This is so you can tell your data apart from everyone else's)
- For each record processed, populate an instance of `EnrichedUser`

#### Consume from "question-1", populate a case class, enrich the data from a map, and write to Kafka

Modify your solution to the problem above by adding the following functionality:
- Convert your instance of `EnrichedUser` into a comma-separated string of values
- Write the comma-separated string to a topic named `question-1-output`
- Terminate your `HweConsumer` process and use `SimpleConsumer` to try and find your data!

### Potential Problems

#### When I run the ConnectionTest, my program just hangs!

There is likely a problem with your IP address - fill out the What's Your IP form again
 and ask a TA to re-approve you.

#### When I run the ConnectionTest, it complains "Failed to construct the kafka consumer!: Failed to load SSL keystore"

If you see an error message like this:
`
Exception in thread "main" org.apache.kafka.common.KafkaException: Failed to construct kafka consumer
...
Caused by: org.apache.kafka.common.KafkaException: org.apache.kafka.common.KafkaException: Failed to load SSL keystore src\main\resources\kafka.client.truststore.jks of type JKS`

then there are 2 things to double check here:

1. Make sure exactly one of the 2 lines below is uncommented, depending if you are on a Windows or a Mac:
`
//Use this for Windows
val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
//Use this for Mac
//val trustStore: String = "src/main/resources/kafka.client.truststore.jks"
`

2. In your Intellij Run Configuration, make sure your "Working Directory" is set to "C:\Path\To\Your\Downloaded\Repo\kafka-hello-world"

#### When I run the ConnectionTest, I get an error about "SaslAuthenticationException: Authentication failed during authentication due to invalid credentials with SASL mechanism SCRAM-SHA-512
"!

If you see an error message like this below, your username or password is incorrect.

`
Exception in thread "main" org.apache.kafka.common.errors.SaslAuthenticationException: Authentication failed during authentication due to invalid credentials with SASL mechanism SCRAM-SHA-512
`

#### Stretch #1 - Theory

What is the CAP theorem, and where does Kafka fit within it?
