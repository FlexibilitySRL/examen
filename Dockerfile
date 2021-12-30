FROM openjdk:15
VOLUME /tmp
EXPOSE 8080
ADD ./target/examen-0.0.1.jar app-examen.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app-examen.jar"]