FROM openjdk:8
ADD target/examen-0.0.1.jar examen-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "examen-0.0.1.jar"]