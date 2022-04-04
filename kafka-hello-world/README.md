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

#### Consume from "question-1"

A topic exists called "question-1" with JSON strings as its messages. Using `JSONConsumer`,

- Print out the string to understand the structure.
- Create a case class that matches this data structure.
- Parse the JSON string into the scala case class that you created.



#### Stretch #1 - Theory

What is the CAP theorem, and where does Kafka fit within it?