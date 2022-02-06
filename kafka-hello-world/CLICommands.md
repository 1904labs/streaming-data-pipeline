# Kafka CLI

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