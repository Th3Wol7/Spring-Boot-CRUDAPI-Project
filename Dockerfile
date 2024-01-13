FROM openjdk:17-jdk-alpine
MAINTAINER tyrien
COPY target/*.jar app.jar
ENTRYPOINT ["java", "jar", "/app.jar"]