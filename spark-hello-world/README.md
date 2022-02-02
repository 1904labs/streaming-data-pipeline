# spark-hello-world
An example Spark application written in Scala and setup using Maven.

# The Lab

## Challenge 1: Run the Batch App

Open up `HelloWorldBatchApp.scala` from `src/main/scala/com/labs1904/hwe`. Right click on MyApp and choose "Run MyApp" 
to run the Spark app locally. If all goes well, you should see output similar to the following in the console:

```
2022-02-01 21:12:18 INFO  HelloWorldBatchApp$:12 - HelloWorldBatchApp starting...
root
 |-- _c0: string (nullable = true)

+-------------------------------------------------+
|_c0                                              |
+-------------------------------------------------+
|Space.                                           |
|The final frontier.                              |
|These are the voyages of the starship Enterprise.|
|It's continuing mission:                         |
|to explore strange new worlds                    |
|to seek out new life and new civilizations       |
|to boldly go where no one has gone before!       |
+-------------------------------------------------+
```

If you don't, check out the Known Issues section below.

## Challenge 2: Run the Structured Streaming App

TODO

## Challenge 3: Connect the Structured Streaming app to Kafka

The structured streaming app requires a Kafka cluster. The data on the topic is from the [Amazon Customer Reviews Dataset](https://registry.opendata.aws/amazon-reviews/)
and looks like the following example:

```json
{
  "marketplace": "US",
  "customer_id": 1,
  "review_id": "R26ZK6XLDT8DDS",
  "product_id": "B000L70MQO",
  "product_parent": 216773674,
  "product_title": "Product 1",
  "product_category": "Toys",
  "star_rating": 5,
  "helpful_votes": 1,
  "total_votes": 4,
  "vine": "N",
  "verified_purchase": "Y",
  "review_headline": "Five Stars",
  "review_body": "Cool.",
  "review_date": "2015-01-12T00:00:00.000-06:00"
}
```

Open up MyStreamingApp.scala from src/main/scala/com/kitmenke/spark.

You will need to specify the Kafka bootstrap servers as the first argument.

# Known Issues

## An illegal reflective access operation has occurred

If you get the following warning, you are using the wrong JDK. You must use JDK version 8 (sometimes called 1.8).

```
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.apache.spark.unsafe.Platform (file:/Users/Kit/.m2/repository/org/apache/spark/spark-unsafe_2.12/3.0.1/spark-unsafe_2.12-3.0.1.jar) to constructor java.nio.DirectByteBuffer(long,int)
WARNING: Please consider reporting this to the maintainers of org.apache.spark.unsafe.Platform
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```

To change this in Intellij, go to File -> Project Structure. Make sure that the right JDK is selected, for example you
may use `correcto-1.8` downloaded through Intellij.

## Scary warnings

You may get some scary warnings. You can ignore the following

```
2022-02-01 20:40:52 WARN  Utils:69 - Your hostname, 1904labss-MacBook-Pro.local resolves to a loopback address: 127.0.0.1; using 192.168.1.163 instead (on interface en0)
2022-02-01 20:40:52 WARN  Utils:69 - Set SPARK_LOCAL_IP if you need to bind to another address
```

this too

```
2022-02-01 20:40:52 WARN  NativeCodeLoader:62 - Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
```

TODO: maybe fix here? https://stackoverflow.com/questions/19943766/hadoop-unable-to-load-native-hadoop-library-for-your-platform-warning

## MacOS can't assign requested address

Problems running locally?

```
spark.conf.set("spark.driver.host", "localhost")
```

## Connecting to a single node cluster

To write data to a single ndoe cluster, you'll need the following config:
```
spark.conf.set("spark.hadoop.dfs.client.use.datanode.hostname", "true")
spark.conf.set("spark.hadoop.fs.defaultFS", "hdfs://quickstart.cloudera:8020")
```

## WinUtils

You may experience the following error on Windows:
```
2018-01-23 17:00:21 ERROR Shell:396 - Failed to locate the winutils binary in the hadoop binary path
java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.
	at org.apache.hadoop.util.Shell.getQualifiedBinPath(Shell.java:378)
	at org.apache.hadoop.util.Shell.getWinUtilsPath(Shell.java:393)
	at org.apache.hadoop.util.Shell.<clinit>(Shell.java:386)
	at org.apache.hadoop.util.StringUtils.<clinit>(StringUtils.java:79)
	at org.apache.hadoop.security.Groups.parseStaticMapping(Groups.java:116)
	at org.apache.hadoop.security.Groups.<init>(Groups.java:93)
	at org.apache.hadoop.security.Groups.<init>(Groups.java:73)
	at org.apache.hadoop.security.Groups.getUserToGroupsMappingService(Groups.java:293)
	at org.apache.hadoop.security.UserGroupInformation.initialize(UserGroupInformation.java:283)
	at org.apache.hadoop.security.UserGroupInformation.ensureInitialized(UserGroupInformation.java:260)
	at org.apache.hadoop.security.UserGroupInformation.loginUserFromSubject(UserGroupInformation.java:789)
	at org.apache.hadoop.security.UserGroupInformation.getLoginUser(UserGroupInformation.java:774)
	at org.apache.hadoop.security.UserGroupInformation.getCurrentUser(UserGroupInformation.java:647)
	at org.apache.spark.util.Utils$$anonfun$getCurrentUserName$1.apply(Utils.scala:2430)
	at org.apache.spark.util.Utils$$anonfun$getCurrentUserName$1.apply(Utils.scala:2430)
	at scala.Option.getOrElse(Option.scala:121)
	at org.apache.spark.util.Utils$.getCurrentUserName(Utils.scala:2430)
	at org.apache.spark.SparkContext.<init>(SparkContext.scala:295)
	at org.apache.spark.SparkContext$.getOrCreate(SparkContext.scala:2509)
	at org.apache.spark.sql.SparkSession$Builder$$anonfun$6.apply(SparkSession.scala:909)
	at org.apache.spark.sql.SparkSession$Builder$$anonfun$6.apply(SparkSession.scala:901)
	at scala.Option.getOrElse(Option.scala:121)
	at org.apache.spark.sql.SparkSession$Builder.getOrCreate(SparkSession.scala:901)
	at com.kitmenke.spark.MyApp$.main(MyApp.scala:22)
	at com.kitmenke.spark.MyApp.main(MyApp.scala)
```

This error is mostly harmless although annoying. To fix it, you'll need to download Hadoop binaries for windows. Luckily, there is a repo with these already.

```
git clone https://github.com/steveloughran/winutils
```

Then, in your IntelliJ run configuration, add an environment variable named `HADOOP_HOME` with the value as the path to wherever you cloned winutils, for example: `C:\Users\USERNAME\Code\winutils\hadoop-2.7.1`

# Other Stuff

For a more complicated project, you may run into conflicts between dependencies. In that case, switch to use the maven-shade-plugin.

    <!-- Alternative to maven-assembly-plugin -->
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
            <createDependencyReducedPom>false</createDependencyReducedPom>
            <keepDependenciesWithProvidedScope>false</keepDependenciesWithProvidedScope>
        </configuration>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>shade</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    
For Cloudera's distribution of Hadoop, add the following to your pom.xml
    
    <!-- Cloudera specific dependencies -->
    <repositories>
        <repository>
            <id>cloudera</id>
            <url>https://repository.cloudera.com/artifactory/cloudera-repos/</url>
        </repository>
    </repositories>
    
