# HWE Local install

This is how to setup your local environment to run the solutions without cloud resources.

## Mac Software install

Install mySQL: `brew install mysql`

Install Kafka: `brew install kafka`

Install Hadoop: `brew install hadoop`

Install Hive: `brew install hive`

Install Spark: `brew install apache-spark`

Install Nifi: `brew install nifi`

Install HBase: `brew install hbase`

## Mac update .bash_profile

add to path:
`/usr/local/Cellar/hive/3.1.3/libexec/bin`
add local pub key to authorized keys:
`cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys`

## Mac start apps

```markdown
brew services restart kafka
brew services restart hbase
nifi start
hive --service metastore &
$HADOOP_HOME/sbin/start-all.sh
```

## Nifi setup

```bash
./bin/nifi.sh set-single-user-credentials hwe_1904labs 1904labsSuperLong
```

or

```bash
grep Generated /usr/local/Cellar/nifi/1.16.1/libexec/logs/nifi-app*log | grep -v 'sun.reflect.GeneratedMethodAccessor'
```

## Mac hive settup

https://techblost.com/how-to-install-hive-on-mac-with-homebrew/

mysql -uroot
CREATE database metastore;
GRANT ALL PRIVILEGES ON  *.* to 'hive'@'localhost';
CREATE user 'hive'@'localhost' identified by 'password123';
FLUSH PRIVILEGES;
mysql -uroot metastore < /usr/local/Cellar/hive/3.1.3/libexec/scripts/metastore/upgrade/mysql/hive-schema-3.1.0.mysql.sql

Open the MySQL official websit
Select Platform Independent -> Download to your machine
Unzip -> Place the jar “mysql-connector-java-8.0.19.jar”  into the /usr/local/Cellar/hive/3.1.2_3/libexec/lib directory

## Mac install spark

https://medium.com/beeranddiapers/installing-apache-spark-on-mac-os-ce416007d79f

## Mac install hadoop

https://techblost.com/how-to-install-hadoop-on-mac-with-homebrew/

## We can check the correct execution in the following way

In a browser enter <http://localhost>:{port number}  
Where **port number** can be:

* <https://localhost:8443/nifi/> (NiFi)

### For Kafka usage

```bash
#To create a TOPIC: 
kafka-topics --create --zookeeper localhost:2181 - -replication-factor 1 --partitions 1 --topic EXAMPLE  

#To verify the build:
kafka-topics --list --zookeeper localhost:2181

#To create a PRODUCER:
kafka-console-producer --broker-list localhost:3030 --topic EXAMPLE

kafka-console-producer --broker-list localhost:2181 --topic EXAMPLE

#To create a CONSUMER:
kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic EXAMPLE`  
```

## Spark edit

```bash
brew install apache-spark
brew edit apache-spark
```

```markdown
class ApacheSpark < Formula
  desc "Engine for large-scale data processing"
  homepage "https://spark.apache.org/"
  url "https://archive.apache.org/dist/spark/spark-2.4.0/spark-2.4.0-bin-hadoop2.7.tgz"
  mirror "https://archive.apache.org/dist/spark/spark-2.4.0/spark-2.4.0-bin-hadoop2.7.tgz"
  version "2.4.0"
  sha256 "c93c096c8d64062345b26b34c85127a6848cff95a4bb829333a06b83222a5cfa"
  license "Apache-2.0"
  head "https://github.com/apache/spark.git"

  depends_on "openjdk@8"

  def install
    # Rename beeline to distinguish it from hive's beeline
    mv "bin/beeline", "bin/spark-beeline"

    rm_f Dir["bin/*.cmd"]
    libexec.install Dir["*"]
    bin.install Dir[libexec/"bin/*"]
    bin.env_script_all_files(libexec/"bin", JAVA_HOME: Formula["openjdk@8"].opt_prefix)
  end

  test do
    assert_match "Long = 1000",
      pipe_output(bin/"spark-shell --conf spark.driver.bindAddress=127.0.0.1",
                  "sc.parallelize(1 to 1000).count()")
  end
end
```

```bash
brew uninstall apache-spark
brew install --build-from-source apache-spark
```

## NIFI Configurations

/Users/slewis/git/1904labs-streaming-data-pipeline/nifi-data-pipeline/core-site.xml,/Users/slewis/git/1904labs-streaming-data-pipeline/nifi-data-pipeline/hdfs-site.xml,/Users/slewis/git/1904labs-streaming-data-pipeline/nifi-data-pipeline/hbase-site.xml

## Adhoc shell commands

```bash
hdfs dfs -ls /data/raw/
hdfs dfs -copyToLocal /data/raw/b46106fe-96df-41e1-a183-14ec27d99b28.snappy.parquet .
parquet-tools schema b46106fe-96df-41e1-a183-14ec27d99b28.snappy.parquet
parquet-tools head b46106fe-96df-41e1-a183-14ec27d99b28.snappy.parquet
```
