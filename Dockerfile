# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jdk-alpine
VOLUME /tmp
# copy WAR into image
COPY target/examen-0.0.1.jar /app.jar 
# run application with this command line 
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=local", "/app.jar"]
# build for docker containers use
#CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=local-mysql-container", "/app.jar"]
 