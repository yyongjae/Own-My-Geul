FROM openjdk:11

WORKDIR /app

COPY . /app

RUN ./gradlew clean build --no-daemon

EXPOSE 8080

CMD ["java", "-jar", "build/libs/omg-spring-boot-app.jar", "--spring.profiles.active=prod"]