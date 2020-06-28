FROM openjdk:8-jdk-alpine
RUN addgroup -S flexibility && adduser -S flexibility -G flexibility
USER flexibility:flexibility
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8080
ENTRYPOINT ["java","-cp","app:app/lib/*","ar.com.flexibility.examen.Application"]