# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jdk-alpine
VOLUME /tmp
# copy WAR into image
COPY target/examen-0.0.1.jar /app.jar 
# run application with this command line 
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=local", "/app.jar"]

# docker cloud image
# sudo docker run -t --name examen-container-simple -p 8080:8080 jonatanduplessy/test-docker-repo:examen-latest-simple
 