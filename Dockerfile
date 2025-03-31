FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/email-0.0.1-SNAPSHOT.jar /app/email-ms.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/email-ms.jar"]

