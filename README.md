# REST Test

# Bienvenidos!

La prueba consiste en agregar nueva funcionalidad a la API REST que corre en este repositorio. Para eso vamos a guiarnos por los siguientes puntos:

1) Hacer un fork del repositorio, crear un nuevo branch y realizar las tareas enunciadas a continuación.

2) Proveer servicios para la administración de la compra de productos. Los mismos deberán incluir:
- ABM de productos.
- ABM de clientes.
- Consulta de transacciones de compra.
- Aprobación de compras.
 
3) Los servicios deben contar con logs que indiquen si el servicio respondió correctamente o no.
  
4) Documentar brevemente los servicios implementados.
 
5) Todos los servicios deben contar, al menos, con test unitarios.
 
6) Enviar un Pull Request con todos los cambios realizados. 

Para correr la aplicación se puede utilizar maven (DB fisica o en memoria): 
- mvn spring-boot:run -Dspring-boot.run.profiles=local
- mvn spring-boot:run -Dspring-boot.run.profiles=test

Pueden probar el servicio de prueba con un curl de la siguiente forma:

`curl -X POST  http://localhost:8080/custom/echo  -H "accept: application/json"  -H "cache-control: no-cache"  -H "content-type: application/json"  -d "{\"message\":\"mensaje de prueba\"}"`

Bonus

1) Hostear la app en un cloud computing libre (Cloudfoudry o APP Engine) y enviar la URL para consultar.
2) ABM de vendedores.
3) Agregar test de integración.
4) Correr pruebas con base de datos en memoria.
5) Calcular la covertura de los tests.
6) Crear Docker Image.

#    

# SOLUCION:

    Tech Stack
        - Java 8
        - Spring Boot
        - Maven
        - MySql 8
        - Swagger
        - Jackson
        - Jacoco

## Instrucciones

Para correr la aplicación usando Mysql ejecutar:

	mvn spring-boot:run -Dspring-boot.run.profiles=local

Para correr la aplicación usando la base de datos en memoria (H2) ejecutar:

	mvn spring-boot:run -Dspring-boot.run.profiles=test
	
Para obtener métricas de Test coverage:

	1) Ejecutar:
		
		mvn clean package
		
	2) Abrir el archvio de reporte que se encuentra en la ruta:
		
		.\target\site\jacoco\index.html
	
Para obtener información sobre los servicios y probar los mismos. Ejecutar la aplicación y acceder via browser a:

	http://localhost:8080/swagger-ui.html

Para acceder a la instancia de Pivotal, acceder via browser a:

	https://flexibility-exam-terrific-gelada-zr.cfapps.io/swagger-ui.html
	
	
	

