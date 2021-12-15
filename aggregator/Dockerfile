FROM openjdk:latest
ADD metrics /home/metrics
ADD target/aggregator-1.0-SNAPSHOT-jar-with-dependencies.jar aggregator.jar
ENTRYPOINT ["java","-jar","aggregator.jar","/home/metrics/"]