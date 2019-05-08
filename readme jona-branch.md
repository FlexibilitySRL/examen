## ABM Product
ProductApi.java,
ProductController.java,
GenericProductException.java,
Product.java,
ProductRepository.java,
ProductServiceImpl.java,
ProductService.java

## Test Coverage Report
ProductControllerIntegrationTest.java,
ProductServiceIntegrationTest.java

product-coverage-jacoco.exec

## Docker
[https://hub.docker.com/r/jonatanduplessy/test-docker-repo/tags](https://hub.docker.com/r/jonatanduplessy/test-docker-repo/tags)

jonatanduplessy/test-docker-repo/mysql-latest, 
jonatanduplessy/test-docker-repo/examen-latest

`sudo docker run -d -p 2012:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=Tecso2019 -e  MYSQL_DATABASE=examen jonatanduplessy/test-docker-repo:mysql-latest`

`sudo docker run -t --name examen-container -link mysql-container:mysql -p 8087:8080 jonatanduplessy/test-docker-repo:examen-latest`

`http://localhost:8087/swagger-ui.html#/`

## SonarCloud
[https://sonarcloud.io/dashboard?id=jonatanduplessy_examen](https://sonarcloud.io/dashboard?id=jonatanduplessy_examen)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jonatanduplessy_examen&metric=alert_status)](https://sonarcloud.io/dashboard?id=jonatanduplessy_examen)