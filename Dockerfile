FROM frolvlad/alpine-java:jdk8-cleaned

ARG SPRING_PROFILES_ACTIVE
ARG STARTUP_SLEEP
ARG JAVA_OPTS
ARG PORT

ENV SPRING_PROFILES_ACTIVE ${SPRING_PROFILES_ACTIVE:-local}
ENV STARTUP_SLEEP ${STARTUP_SLEEP:-0}
ENV JAVA_OPTS ${JAVA_OPTS:-'-Xmx512m'}
ENV DEBUG_OPTS ${DEBUG_OPTS}
ENV PORT ${PORT:-8080}

COPY target/examen-0.0.1.jar /app.jar

VOLUME /tmp

RUN sh -c 'touch /app.jar'

EXPOSE ${PORT}

CMD echo "The application will start in ${STARTUP_SLEEP}s..." && \
 sleep ${STARTUP_SLEEP} && \
 java ${JAVA_OPTS} ${DEBUG_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
