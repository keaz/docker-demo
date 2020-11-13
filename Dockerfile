FROM adoptopenjdk/maven-openjdk11:latest as build

WORKDIR /build

COPY src src
COPY pom.xml .

RUN mvn clean install -DskipTests

FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine

WORKDIR /app
COPY --from=build /build/target/DockerDemo-1.0-SNAPSHOT.jar .

VOLUME /date

ENTRYPOINT java -jar DockerDemo-1.0-SNAPSHOT.jar