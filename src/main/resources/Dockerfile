FROM openjdk:8-jdk-alpine
MAINTAINER juan.atto@gmail.com
ARG JAR_FILE=target/examen-0.0.1.jar
COPY ${JAR_FILE} examen-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","examen-0.0.1.jar", "--server.port=8080"]
