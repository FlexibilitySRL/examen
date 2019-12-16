FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN ["chmod", "+x", "./wait-for-it.sh"]
ENTRYPOINT ["./wait-for-it.sh", "examen-mysql:3306","-t","120", "--", "java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]