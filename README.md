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
- [Introduction to Kafka Streams in Java by Baeldung](https://www.baeldung.com/java-kafka-streams)
- [Kafka Streams and Spring Cloud Stream by Pivotal](https://spring.io/blog/2018/04/19/kafka-streams-and-spring-cloud-stream)
- [Spring Cloud Stream with Kafka by DZone](https://dzone.com/articles/spring-cloud-stream-with-kafka)
- [Spring Boot Actuator metrics monitoring with Prometheus and Grafana by Callicoder](https://www.callicoder.com/spring-boot-actuator-metrics-monitoring-dashboard-prometheus-grafana/)

## Platform _(out of scope)_
- Kafka broker running in a [dockerized Confluent Platform](https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html) in local machine, reachable at localhost:9092
- Prometheus server running in a docker container _(Prometheus config file at `/config/prometheus`)_, reachable at localhost:9090
`docker run -d --name=prometheus -p 9090:9090 -v ./prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml`