La prueba consiste en agregar nueva funcionalidad a la API REST que corre en este repositorio. Para eso vamos a guiarnos por los siguientes puntos:

1) **[Done]** Hacer un fork del repositorio, crear un nuevo branch y realizar las tareas enunciadas a continuación.

2) Proveer servicios para la administración de la compra de productos. Los mismos deberán incluir:
- **[Done]** ABM de productos.
- **[Done]** ABM de clientes.
- **[Done]** Consulta de transacciones de compra.
- **[Done]** Aprobación de compras.
 
3) **[Done]** Los servicios deben contar con logs que indiquen si el servicio respondió y proceso correctamente o no. **Para el logeo de los servicios utilice aspectos con annotations y para logear los restControllers utilice aspectos con expresiones regulares.**

  
4) **[Done]** Documentar brevemente los servicios implementados.
 
5) **[Done]** Todos los servicios deben contar, al menos, con test unitarios.
 
6) **[Done]** Enviar un Pull Request con todos los cambios realizados. 

Para correr la aplicación se puede utilizar maven: 

mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"

Pueden probar el servicio echo con un curl de la siguiente forma:

`curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"message":"mensaje de prueba"}' localhost:8080/payments/echo`

Bonus

1) **[Done]** ABM de vendedores.
2) Agregar test de integración.
3) **[Done]** Calcular la cobertura de los tests.
4) **[Done]** Correr pruebas con base de datos en memoria.
5) **[Done]** Crear Docker Image.
6) Hostear la app en un cloud computing libre y enviar la URL para consultar.

Otras instrucciones:

- Swagger

        URL de swagger: http://localhost:8080/payments/swagger-ui.html#/


- Instruciones para correr la imagen docker

    Abrir una consola en la raiz del proyecto y ejecutar:

1) docker-compose build

2) docker-compose up


- Cobertura de tests

    Despues de hacer mvn install, abrir en un navegador el siguiente archivo: target/site/jacoco/index.html