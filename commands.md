### Testing
```
mvn clean test
```

**Reports**
> surefire-reports/*

**Coverage**
> target/jacoco.exec

### Sonar

```
mvn sonar:sonar \
-Dsonar.projectKey=jonatanduplessy_examen \ 
-Dsonar.organization=jonatanduplessy-github \
-Dsonar.host.url=https://sonarcloud.io \  
-Dsonar.login=8913a970571ed75b2d21396a1733036d41ca293c
```

[https://sonarcloud.io/dashboard?id=jonatanduplessy_examen](https://sonarcloud.io/dashboard?id=jonatanduplessy_examen)

### Docker
```
mvn clean install
sudo docker build -t jonatanduplessy/test-docker-repo:examen-latest-simple .
sudo docker push jonatanduplessy/test-docker-repo:examen-latest-simple
sudo docker run -t --name examen-container-simple -p 8080:8080 jonatanduplessy/test-docker-repo:examen-latest-simple
```

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

[https://hub.docker.com/r/jonatanduplessy/test-docker-repo/tags](https://hub.docker.com/r/jonatanduplessy/test-docker-repo/tags)

### App Hosting

eclipse view 'Boot Dashboard'

[https://console.run.pivotal.io/](https://console.run.pivotal.io/)

[https://examen.cfapps.io/swagger-ui.html](https://examen.cfapps.io/swagger-ui.html)


