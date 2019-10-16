# kafkastreams-spring-cloud-stream-expl

## Intro
Demo Project for [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) with [Apache Kafka Streams](https://kafka.apache.org/documentation/streams/)

## Fwks., Tools and Versions
- Java 1.8
- Spring Boot 2.1.7.RELEASE
- Spring Cloud Greenwich.RELEASE
- Kafka 2.0.1
- [Lombok](https://projectlombok.org/) 1.18.1
- [AssertJ](https://joel-costigliola.github.io/assertj/) 3.13.2
- [Pragmatists/JUnitParams](https://github.com/Pragmatists/JUnitParams) 1.1.1
- [Micrometer Prometheus](https://micrometer.io/docs/registry/prometheus) 1.2.0

## References

### Kafka Streams
- [Introduction to Kafka Streams in Java _(by Baeldung_)](https://www.baeldung.com/java-kafka-streams)
- [Kafka Streams and Spring Cloud Stream _(by Pivotal)_](https://spring.io/blog/2018/04/19/kafka-streams-and-spring-cloud-stream)
- [Spring Cloud Stream with Kafka _(by DZone)_](https://dzone.com/articles/spring-cloud-stream-with-kafka)
- [Spring Boot Actuator metrics monitoring with Prometheus and Grafana _(by Callicoder)_](https://www.callicoder.com/spring-boot-actuator-metrics-monitoring-dashboard-prometheus-grafana/)
- [Testing an Apache Kafka Integration within a Spring Boot Application_(by Mimacomblog)_](https://blog.mimacom.com/testing-apache-kafka-with-spring-boot/)
- [Unit Testing Kafka Consumers _(by Jesse-Anderson.com)_](https://www.jesse-anderson.com/2016/11/unit-testing-kafka-consumers/)
- [Quick and Practical Example of Kafka Testing _(by DZone)_](https://dzone.com/articles/a-quick-and-practical-example-of-kafka-testing)
- [Spring kafka Embedded Unit Test Example _(by Codenotfound)_](https://codenotfound.com/spring-kafka-embedded-unit-test-example.html)

### Apache Avro
- [Spring Boot + Kafka + Schema Registry](https://medium.com/@sunilvb/spring-boot-kafka-schema-registry-c6e32485a7c8)
- [Confluent Application Development](https://docs.confluent.io/current/app-development/index.html)
- [Confluent Schema Registry API Reference](https://docs.confluent.io/current/schema-registry/develop/api.html)

## Platform _(out of scope)_
- Kafka broker running in a [dockerized Confluent Platform](https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html) in local machine, reachable at localhost:9092
- Schema Registry running in a [dockerized Confluent Platform](https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html) in local machine, reachable at localhost:8081 _(e.g.: GET http://localhost:8081/schemas/ids/21)_
- Prometheus server running in a docker container _(Prometheus config file at `/config/prometheus`)_, reachable at localhost:9090
