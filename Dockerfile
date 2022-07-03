FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} examen.jar
ENTRYPOINT ["java","-jar","/examen.jar"]