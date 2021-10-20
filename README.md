# REST Test

# Bienvenidos!

La prueba consiste en agregar nueva funcionalidad a la API REST que corre en este repositorio. Para eso vamos a guiarnos por los siguientes puntos:

1) -[x] Hacer un fork del repositorio, crear un nuevo branch y realizar las tareas enunciadas a continuación.

2) -[x] Proveer servicios para la administración de la compra de productos. Los mismos deberán incluir:
- ABM de productos.
- ABM de clientes.
- Consulta de transacciones de compra.
- Aprobación de compras.
 
3) -[x] Los servicios deben contar con logs que indiquen si el servicio respondió y proceso correctamente o no.
  
4) -[x] Documentar brevemente los servicios implementados.
 
5) -[x] Todos los servicios deben contar, al menos, con test unitarios.
 
6) -[x] Enviar un Pull Request con todos los cambios realizados. 

Para correr la aplicación se puede utilizar maven: 

mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"

Pueden probar el servicio echo con un curl de la siguiente forma:

`curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"message":"mensaje de prueba"}' localhost:8080/payments/echo`

Bonus

1) -[x] ABM de vendedores.
2) Agregar test de integración.
3) Calcular la cobertura de los tests.
4) Correr pruebas con base de datos en memoria.
5) -[x] Crear Docker Image.
6) -[x] Hostear la app en un cloud computing libre y enviar la URL para consultar.

#### URL Swagger
hexacta-test-app.herokuapp.com/payments/swagger-ui.html

#### Create Docker Image
docker build -t hexacta-test-app:latest .  



