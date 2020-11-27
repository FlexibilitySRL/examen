FROM openjdk:8-jdk-alpine
MAINTAINER manfredi julian
VOLUME /tmp
EXPOSE 8080
ADD target/examen-0.0.1.jar examen.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/examen.jar"]