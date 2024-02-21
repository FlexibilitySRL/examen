FROM openjdk:8

EXPOSE 8080
ADD target/examen-0.0.1.jar /app/examen.jar
WORKDIR /app
CMD java -jar examen.jar