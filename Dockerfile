FROM openjdk:11-jdk

RUN mkdir /app
WORKDIR /app

COPY build/libs/rest-spring-boot-1.0.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]