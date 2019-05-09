## App Hosted
[https://examen.cfapps.io/swagger-ui.html](https://examen.cfapps.io/swagger-ui.html)

## Sonar Cloud

`mvn sonar:sonar   -Dsonar.projectKey=jonatanduplessy_examen   -Dsonar.organization=jonatanduplessy-github   -Dsonar.host.url=https://sonarcloud.io   -Dsonar.login=8913a970571ed75b2d21396a1733036d41ca293c`

[https://sonarcloud.io/dashboard?id=jonatanduplessy_examen](https://sonarcloud.io/dashboard?id=jonatanduplessy_examen)

## Test Coverage

`mvn clean test`

Reports
> surefire-reports/TEST-ar.com.flexibility.examen.app.rest.ClientControllerIntegrationTest.xml

> surefire-reports/TEST-ar.com.flexibility.examen.app.rest.ProductControllerIntegrationTest.xml

> surefire-reports/TEST-ar.com.flexibility.examen.domain.service.ClientServiceIntegrationTest.xml

> surefire-reports/TEST-ar.com.flexibility.examen.domain.service.ProcessMessageServiceTest.xml

> surefire-reports/TEST-ar.com.flexibility.examen.domain.service.ProductServiceIntegrationTest.xml

Coverage
> target/jacoco.exec


## Docker 

[https://hub.docker.com/r/jonatanduplessy/test-docker-repo/tags](https://hub.docker.com/r/jonatanduplessy/test-docker-repo/tags)

`mvn clean install`

`sudo docker build -t jonatanduplessy/test-docker-repo:examen-latest-simple .`

`sudo docker push jonatanduplessy/test-docker-repo:examen-latest-simple`

`sudo docker run -t --name examen-container-simple -p 8080:8080 jonatanduplessy/test-docker-repo:examen-latest-simple`

`http://localhost:8080/swagger-ui.html#/`