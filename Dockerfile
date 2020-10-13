FROM openjdk:8-jre-alpine

ARG WAR_FILE=target/examen-0.0.1.war

COPY ${WAR_FILE} /examen.war

CMD ["/usr/bin/java", "-jar", "/examen.war"]