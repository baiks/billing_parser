# Introduction 
The project entails building a microservice spring-boot application that will be used in parsing data into
respective formats as requested by the user (another service)

# Getting Started
How to deploy the service
1. Have java8 -> java14 running on your host machine (choose one java version)
2. For development purposes install maven(build tool for spring-boot applications)

# Build and Test
1. In development Environment
    - Use maven to build and run the application
       - mvn clean
       - mvn package -DskipTests (generates a jar file subject to deployment)
       - mvn spring-boot:run
    - TODO : Alternatively use gradle to build and package application
    
2. In production 
    - Deploy a jar file
       - java -jar name_of_application.jar 

# Recommendations
- For internal communication between micro services maintained by the Equity Team use messaging queues by use of messaging brokers
  - Available options for message brokers
     1. ActiveMQ
     2. RabbitMQ
     3. Kafka : I recommend using kafka because of :
        - Its ability to persist messages
        - Handles lots of writes per second (2M)
        - It easy to scale kafka by using clusters
        
- For structural data storage use mysql or postgres

# Contribute
1.Steps to start contributing to the project
     - clone the project and create a feature branch off develop
     - push your changes to your feature branch
     - create a pr to the maintainer of the project 
     - If your pr is reviewed and accepted your changes will be merged to develop
     
Useful Resources to help 
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)

#Kafka Documentation
Installation
1. Download kafka ,have default java jdk installed java11
    - Download command wget http://www-us.apache.org/dist/kafka/2.4.0/kafka_2.13-2.4.0.tgz
2. Extract the download 
    - tar xzf kafka_2.13-2.4.0.tgz
3. Move extract to /usr/local/kafka
    - mv kafka_2.13-2.4.0 /usr/local/kafka
4. Write services to start and stop kafka and zookeeper
    - zookeeper.service
    - kafka.service
5. Reload the systemd daemon to apply new changes.
6  Start zookeeper then start kafka

USAGE
Creating a topic
- /usr/local/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testTopic
List topics
- /usr/local/kafka/bin/kafka-topics.sh --list --zookeeper localhost:2181
Send Messages to Kafka
- /usr/local/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic testTopic
Using Kafka Consumer
- /usr/local/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic testTopic --from-beginning

#### Items for Sprint3
- Documentation on Readme File
- Logging Logback
- Project Structure
- Error Handling
- Api - Testing
- Data layer and repository
- Adding Swagger Documentation
- Xml parsing
- Json Parsing

### Test Jsonrpc
curl -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"parseJson","params":{"data":{"data":"1"}}}' http://localhost:8909/json-rpc
