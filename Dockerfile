FROM openjdk:11
ADD target/test-app.jar test-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "test-app.jar"]