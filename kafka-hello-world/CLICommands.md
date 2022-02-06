# Kafka CLI

## Set the bootstrap-server environment variable

We store the list of hosts we use to connect to Kafka in 
[environment variables](https://www.twilio.com/blog/2017/01/how-to-set-environment-variables.html). All of the
instructions below are commands you can use after [installing Kafka locally](https://kafka.apache.org/quickstart). The
commands will also work with a remote cluster just by changing your `BOOTSTRAP_SERVERS` environment variable to the
correct hosts.

### Windows

```
SET BOOTSTRAP_SERVERS=localhost:9092
```


### Linux/Mac

```
export BOOTSTRAP_SERVER=localhost:9092
```

## Kafka-Topics

### Create

Creates a topic called orders, with six partitions and two replications.

```
kafka-topics --bootstrap-server 127.0.0.1:9092 --topic orders --create --partitions 6 --replication-factor 2
```

### Describe

```
kafka-topics --bootstrap-server 127.0.0.1:9092 --topic orders --describe
```

### List

```
kafka-topics --bootstrap-server 127.0.0.1:9092 --list
```

### Delete

```
kafka-topics --bootstrap-server 127.0.0.1:9092 --topic orders --delete
```

## Kafka Producer

```
kafka-console-producer --broker-list 127.0.0.1:9092 --topic orders
kafka-console-producer --broker-list 127.0.0.1:9092 --topic orders --producer-property acks=1
kafka-console-producer --broker-list 127.0.0.1:9092 --topic orders --property parse.key=true --property key.separator=,
<!-- key, value -->
```

## Kafka Consumer

```
kafka-console-consumer --bootstrap-server=127.0.0.1:9092 --topic orders
kafka-console-consumer --bootstrap-server=127.0.0.1:9092 --topic orders --from-beginning
kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic orders --property print.key=true --property key.separator=,
```

### Consumer Groups

```
kafka-console-consumer --bootstrap-server=127.0.0.1:9092 --topic orders --group my-first-application
kafka-consumer-groups --bootstrap-server=127.0.0.1:9092 --list
kafka-consumer-groups --bootstrap-server=127.0.0.1:9092 --describe --group my-first-application
```

Running more consumer groups than there are partitions will result in an inactive group.