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

------------------------------------------------------------------------------------------------------

Resolución

1) Se realiza un fork del proyecto y se crea el branch prueba
2) Se crea ABM de productos,clientes,Consulta de transacciones y Aprobación de compras.
3) Se loguean las respuestas en los servicios.
4) Se realizan test de la capa de servicios.
5) Se documentan brevemente los servicios ( Se genera JAVAdoc).
6) Se envia pull request.


Bonus

1) Se crea Abm de vendedores (Seller)
5) Se crea dockerfile y entorno completo con docker-compose.
6) Se hostea en heroku usando base de datos POSTGRESQL y se incluye swagger de la api expuesta.

url: https://flex-examen.herokuapp.com/swagger-ui.html
