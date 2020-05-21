FROM openjdk:8
ADD target/flexibility-exam.jar flexibility-exam.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "flexibility-exam.jar"]