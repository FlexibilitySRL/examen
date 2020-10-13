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


___________________________________________________________________________________________________________________________________________________________

# Solución propuesta

·Se agrega la funcionalidad pedida en la API REST dada.

·Se hace uso de base de datos en memoria.

·El log de los servicios fue resuelto utilizando un interceptor (LoggerInterceptor) el cual intercepta todos los requests que sean dirigidos a los controladores de 
determinados paths.

·La documentación de los servicios expuestos se realizó utilizando Swagger. Para acceder a esta documentación basta con ir a la url: 
	
	Local: http://localhost:8080/payments/swagger-ui/
	Desplegado remoto: https://challenge-flex-cromero.herokuapp.com//payments/swagger-ui/
	

·Para calcular la cobertura de los tests se utilizó Jacoco. Para generar el reporte es necesario abrir una consola, posicionarse sobre el directorio raíz /examen y correr el siguiente comando:
	
	 mvn clean test
	
Para ver el reporte es necesario abrir el archivo index.html ubicado en /examen/target/site/jacoco/

·Para generar la imagen de Docker se utiliza el archivo /examen/Dockerfile. Es necesario abrir una consola, posicionarse en el directorio raíz /examen y correr el siguiente comando:
	
	 docker build -t examen-flex-cromero:1.0
	
Finalmente para montar la imagen en un contenedor es necesario correr lo siguiente:
	
	 docker run -d -p 8080:8080 --name examen-flex-cromero-cont examen-flex-cromero:1.0
	

·La aplicación se encuentra hosteada en Heroku (https://challenge-flex-cromero.herokuapp.com/payments/)

·Se agregan dos archivos en el directorio raíz que contienen las colecciones de requests tanto para las pruebas locales como para las pruebas contra Heroku. Estos archivos pueden ser importados en Postman.

·Por último, el archivo data.sql agrega información básica de prueba:
	
	· Se insertan 2 Clientes.
	· Se insertan 2 Vendedores.
	· Se insertan 8 Productos.
	· Se insertan 4 Compras con distinta cantidad de productos.
	

