FROM openjdk:8-jdk-alpine
ADD target/flexibility.jar flexibility.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "flexibility.jar"]