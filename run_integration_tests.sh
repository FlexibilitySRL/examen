#!/bin/bash

#######################################
# Pruebas de integración
#######################################

# Se requiere tener instalado newman. 
# Para más información acceda a:
# https://learning.postman.com/docs/running-collections/using-newman-cli/command-line-integration-with-newman/
#

newman run Payment\ Integration\ Test.postman_collection.json

