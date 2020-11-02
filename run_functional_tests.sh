#!/bin/bash

#######################################
# Pruebas de integración
#######################################

# Se requiere tener instalado newman. 
# Para más información acceda a:
# https://learning.postman.com/docs/running-collections/using-newman-cli/command-line-integration-with-newman/
#

# Para correr por primera vez:
# 	$ npm install newman
# 	$ docker-compose up -d mysql-docker-container


newman run 'Functional Tests.postman_collection.json' -e 'Functional Tests - localhost-8080.postman_environment.json'


