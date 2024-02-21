FROM openjdk:8

EXPOSE 8080
ADD target/examen-0.0.1.jar /app/examen.jar
WORKDIR /app
ENV DB_PLATFORM org.hibernate.dialect.H2Dialect
ENV DB_URL jdbc:h2:mem:testdb
ENV DB_USER root
ENV DB_ROOT_PASSWORD password
ENV DATABASE payments
ENV SPRING_PROFILES_ACTIVE dev
CMD java -jar examen.jar