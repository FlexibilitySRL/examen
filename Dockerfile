FROM openjdk:8-jdk-slim
COPY "./target/examen-0.0.1.jar" "examenDocker.jar"
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "examenDocker.jar" ]