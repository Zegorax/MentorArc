FROM maven AS builder
RUN mkdir /tmp/build
COPY . /tmp/build
RUN cd /tmp/build && mv src/main/resources/application.properties.production src/main/resources/application.properties && mvn -B -DskipTests clean package

FROM java:8-jdk-alpine
WORKDIR /usr/app
COPY --from=builder /tmp/build/target/MentorArc-0.0.1-SNAPSHOT.jar /usr/app/

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "MentorArc-0.0.1-SNAPSHOT.jar"]