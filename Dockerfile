FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine as builder

COPY target/DockerDemo-1.0-SNAPSHOT.jar .
VOLUME /date
RUN ls -al
ENTRYPOINT java -jar DockerDemo-1.0-SNAPSHOT.jar

