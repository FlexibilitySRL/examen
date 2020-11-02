
FROM openjdk:8-jre-alpine
LABEL maintainer=“hellraiserii@gmail.com”

VOLUME /tmp

#Agregamos curl para probar conectividad con otros containers
RUN apk --no-cache add curl

RUN addgroup -S examen && adduser -S examen -G examen

USER examen

ADD target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
