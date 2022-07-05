FROM openjdk:8-jdk-alpine AS final

ENV DEBUG "false"

COPY /target/examen*.jar examen.jar

CMD ["java", "-jar", "examen.jar"]