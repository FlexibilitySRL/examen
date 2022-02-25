FROM openjdk:8-alpine
MAINTAINER pablohincapie@hotmail.com
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /examen-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/examen-0.0.1.jar"]