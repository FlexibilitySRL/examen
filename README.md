# REST Test

# Bienvenidos!

La prueba consiste en agregar nueva funcionalidad a la API REST que corre en este repositorio. Para eso vamos a guiarnos por los siguientes puntos:

1) Hacer un fork del repositorio, crear un nuevo branch y realizar las tareas enunciadas a continuación. ✅

2) Proveer servicios para la administración de la compra de productos. Los mismos deberán incluir:
- ABM de productos. ✅
- ABM de clientes. ✅
- Consulta de transacciones de compra. ✅
- Aprobación de compras. ✅
 
3) Los servicios deben contar con logs que indiquen si el servicio respondió y proceso correctamente o no. ✅
  
4) Documentar brevemente los servicios implementados. ✅
 
5) Todos los servicios deben contar, al menos, con test unitarios. ✅
 
6) Enviar un Pull Request con todos los cambios realizados. 

Para correr la aplicación se puede utilizar maven: 

mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"

Pueden probar el servicio echo con un curl de la siguiente forma:

`curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"message":"mensaje de prueba"}' localhost:8080/payments/echo`

Bonus

1) ABM de vendedores. ✅
2) Agregar test de integración. ✅
3) Calcular la cobertura de los tests. ✅
4) Correr pruebas con base de datos en memoria. ✅
5) Crear Docker Image. ✅
6) Hostear la app en un cloud computing libre y enviar la URL para consultar.


Documentacion: 

Postman -> https://www.getpostman.com/collections/48652a661900f2a7eb2a
Swagger -> http://localhost:8080/payments/swagger-ui.html#/ 


Docker:

1. Ejecutar en terminal: mvn clean install
2. Ejecutar en terminal: docker build -t springio/gs-spring-boot-docker .


Cobertura de tests:

1. Ejecutar en terminal: mvn clean install
2. Navegar a la ruta: target/site/jacoco
3. Hacer click en el archivo index.html (Debe abrirse con un navegador web)