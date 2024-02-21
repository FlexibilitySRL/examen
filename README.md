# REST Test

# Bienvenidos!

La prueba consiste en agregar nueva funcionalidad a la API REST que corre en este repositorio. Para eso vamos a guiarnos por los siguientes puntos:

1) Hacer un fork del repositorio, crear un nuevo branch y realizar las tareas enunciadas a continuación.

2) Proveer servicios para la administración de la compra de productos. Los mismos deberán incluir:
- ABM de productos.
- ABM de clientes.
- Consulta de transacciones de compra.
- Aprobación de compras.
 
3) Los servicios deben contar con logs que indiquen si el servicio respondió y proceso correctamente o no.
  
4) Documentar brevemente los servicios implementados.
 
5) Todos los servicios deben contar, al menos, con test unitarios.
 
6) Enviar un Pull Request con todos los cambios realizados. 

Para correr la aplicación se puede utilizar maven: 

mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"

Pueden probar el servicio echo con un curl de la siguiente forma:

`curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"message":"mensaje de prueba"}' localhost:8080/payments/echo`

Bonus

1) ABM de vendedores.
2) Agregar test de integración.
3) Calcular la cobertura de los tests.
4) Correr pruebas con base de datos en memoria.
5) Crear Docker Image.
6) Hostear la app en un cloud computing libre y enviar la URL para consultar.

Solucion By Felix Sirit

Pasos para levantar la aplicacion:

1) Ejecutar el comando: mvn clean package
2) En local se puede levantar con: `mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local`
3) Para generar la imagen Docker y levantar con: `docker compose up`, toma el perfil dev por defecto

Para dar solucion al examen, se desarrollo con las siguientes caracteristicas:
1) El servicio se empleo con arquitectura hexagonal, compuesto por la capa de: application, domain, infrastructure y shared.
2) Se agrego liquibase, para la gestion y versionado de la db.
3) Se agregaron test Unitarios, y test de integracion, lavantando una base de datos en memoria y precardado los datos con liquibase
4) El la convertura de test fue de 88%, el reporte generado se encuenta en : **/target/site/jacoco/index.html
5) Se agregaron servicios en interceptores para logs a nivel de request y response de los servicios REST
6) Se agrego documentacion de la api, la cual esta disponible al levantar el servicios en http://localhost:8080/payments/swagger-ui/index.html
7) Se creo la imagen Docker para levantar la aplicacion
8) El hosting fue en AWS mediente la ruta para acceder al swagger: http://fsirit-examen-env.eba-ep3zpf5q.us-east-2.elasticbeanstalk.com/payments/swagger-ui/index.html
