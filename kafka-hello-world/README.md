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

#### Describe the topic "question-1"

A topic exists called "question-1". Use the command line to describe the topic "question-1":

- How many partitions does it have?
- What is the replication factor?
- Hint: There is an example describe command in the `CLICommands.md`

#### Create a topic "question-2"

Using the Kafka CLI, create a topic with 3 partitions and a replication factor of 3. Call the topic "
question-2-yourname".

#### Consume from "question-3"

A topic already exists called "question-3". Using `SimpleConsumer`, what are the first ten messages at the beginning of
this topic?

#### Produce to "question-4"

Using `SimpleProducer`, produce your name as a string to the topic "question-4". Verify it is there by
using `SimpleConsumer` to read from the "question-4" topic

#### Consume from "question-5"

A topic exists called "question-5" with JSON strings as its messages. Using `JSONConsumer`,

- Print out the string to understand the structure.
- Create a case class that matches this data structure.
- Parse the JSON string into the scala case class that you created.

#### Stretch #1 - Theory

What is the CAP theorem, and where does Kafka fit within it?