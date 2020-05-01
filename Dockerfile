FROM openjdk:8-jdk-alpine

LABEL MAINTAINER="Amador Cuenca <sphi02ac@gmail.com>"

RUN apk update && apk upgrade

ENV INSTALL_PATH '/app'
RUN mkdir -p ${INSTALL_PATH}
WORKDIR ${INSTALL_PATH}

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY run.sh ./run.sh

RUN sed -i 's/\r$//' ./run.sh
RUN chmod +x ./run.sh
RUN chmod +x ./app.jar

CMD sh ./run.sh
