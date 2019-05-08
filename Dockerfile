# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jdk-alpine
VOLUME /tmp
# copy WAR into image
COPY target/examen-0.0.1.jar /app.jar 
# run application with this command line 
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=local-mysql-container", "/app.jar"]

# docker cloud images
# sudo docker run -d -p 2012:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=Tecso2019 -e  MYSQL_DATABASE=examen jonatanduplessy/test-docker-repo:mysql-latest
# sudo docker run -t --name examen-container -link mysql-container:mysql -p 8087:8080 jonatanduplessy/test-docker-repo:examen-latest
 