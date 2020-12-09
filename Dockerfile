FROM openjdk:8
VOLUME /tmp
EXPOSE 8080
ADD ./target/examen-0.0.1.jar examen.jar
ENTRYPOINT ["java","-jar","/examen.jar"]