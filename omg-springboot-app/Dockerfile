FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/*-SNAPSHOT.jar

COPY ${JAR_FILE} omg-springboot-app

CMD ["java", "-jar", "/omg-springboot-app", "—spring.profiles.active=prod"]