# REST Test

# Bienvenidos!

La prueba consiste en agregar nueva funcionalidad a la API REST que corre en este repositorio.


Introducción


Si bien es muy comun utilizar el pattern  DTO (Data Transfer Object) , es este ejemplo no parecia conveniente ya que el modelo era simple y era lo mismo que los parametros de los controllers. Los DTOs, y utilizando las buenas prácticas de "AGGREGATE DTO" deben utilizarse para desacomplar los parametros de entrada de las entidades. Es una soluciòn a monitoriear porque tambien es un antipatternos si no se usa con las buenas prácticas de Agregates. En esta ocasiòn me parecio mejor no utilizarlo pero reconozco los puntos en una aplicación cuendo son necesarios sobre todo el desacoplamiento de interfaces. 

Las entidades son clases que fueron diseñadas para mapear contra la base de datos, no para ser una vista para una pantalla o servicio determinado, lo que provoca que muchos de los campos no puedan ser serializables, no contengan todos los campos necesarios un servicio, ya sea que tengan de más o de menos. Solo a modo de prueba de concepto y por el poco tiempo que tuve para realizar el proyecto, utilice la entity como respuesta a los servicios. Estoy consiente que para logicas mas complejas se debe utilizar los DTOS.

A continuacion se detallan los puntos desarrollados.

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

`curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"message":"mensaje de prueba"}' localhost:8080/api/payments/echo`

Para probar el servicio con docker, seguir los siguientes pasos:


Generar docker image: 

`docker build -t "examen_api" .`

Correr container: 

`docker run -p 8080:8080 "examen_api"`

Bonus

1) ABM de vendedores.
2) Agregar test de integración.
3) Calcular la cobertura de los tests.
4) Correr pruebas con base de datos en memoria.
5) Crear Docker Image.
6) Hostear la app en un cloud computing libre y enviar la URL para consultar.


Los diferentes urls nos llegan al servicio en funcionamiento y la documentación swagger.

Documentación de la api con postman:

- [Postman](https://documenter.getpostman.com/view/14355310/UVeDtT1r)

Api Hosteada:

- Servicio hosteado en Heroku

  - [base url](https://prueba-springboot-rest.herokuapp.com/api/payments)

- Doc de la api hosteada en heroku

  - [Swagger](https://prueba-springboot-rest.herokuapp.com/api/payments/swagger-ui.html)







