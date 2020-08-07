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

Para correr la aplicación se puede utilizar maven: 

mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"

Pueden probar el servicio de prueba con un curl de la siguiente forma:

`curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"message":"mensaje de prueba"}' localhost:8080/custom/echo `

Bonus

1) Hostear la app en un cloud computing libre (Cloudfoudry o APP Engine) y enviar la URL para consultar.
2) ABM de vendedores.
3) Agregar test de integración.
4) Correr pruebas con base de datos en memoria.
5) Calcular la covertura de los tests.
6) Crear Docker Image.

# Solución y Entrega

## Retos cumplidos

Se logró dar solución a la mayoria de los retos propuestos. Solo quedaron pendientes los puntos 1 (Hostear la app...) y 6 (Crear Docker Image) de la sección 'Bonus'.

## Ejecución la solución

La aplicación tiene configurado dos perfiles: local y dev. Se puede ejecutar la aplicación de las siguientes maneras:

1) mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"	Este perfil tiene configurada la conexión a base de datos mysql.
2) mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=dev"	Este perfil tiene configurada la conexión a base de datos H2 (en memoria)

La configuración de los perfiles se encuentra en el archivo application.yml (ubicado en el directorio 'src/main/resources')

La aplicación se expone en el puerto (por defecto) 8080.

## Scipts y base de datos

Se incluye la creación de scripts para gestionar los elementos den base de datos. Los archivos se ubican en el directorio 'src/main/resources', son los siguientes:

- data-h2.sql => Este script contiene los registros para iniciar la base de datos H2 (solo aplica para el perfil 'test')
- schema-mysql.sql => Contiene los DDL para la creeación los elementos (entidades, indíces y demas) en base de datos.

## Pruebas unitarias e integración

La ejecución de las pruebas se puede observar al ejecutar el comando: **mvn test**. 

Las prueba de integración están configuradas para que se realice la gestión de datos en la base H2. 

Nota: Las pruebas de integración se ubican en el directorio 'src/test/java/ar/com/flexibility/examen/app/rest/'

## Documentación

Se gestiona la documentación haciendo uso de la librería 'swagger'. Una vez se ejecute la aplicación, se podrá acceder a la documentación en:

http://localhost:8080/swagger-ui.html

## Covertura de código

Se hace uso de la herramienta 'jacoco' para calcular la covertura de código. 
Para poder observar la covertura se debe:
1) Ejecutar el comando: **mvn clean verify** (puede tambier ejecutar **mvn clean install**)
2) Ubicarse en el directorio 'target/site/jacoco-ut'
3) Localizar el archivo 'index.html' el cual se puede abrir en un navegador (chrome, mozilla etc.)

Nota: La configuración de la covertura de código se encuentra en el archivo 'pom.xml'

## Diseño

Se logra realizar la gestión de clientes, vendedores, productos y ventas.

- El producto cuenta con: código (String - campo único), cantidad disponible (int), precio (double) y un nombre (string)
- El cliente y el vendedor cuenta con: identificación (String - campo único), nombre (string) y apellido (string)
- La venda cuenta con: código (string - único), código del producto (string), cantidad solicitada del producto (int), identificador del cliente (string), identificador del vendedor (string), fecha de compra (date), fecha de aprobación (date), estado (string - PENDIENTE o APROBADO) y un valor (double - Se calcula a partir de la cantidad solicitada y el valor del producto)

## Reglas de negocio

- Un producto puede tener desde 1 hasta 100 unidades disponibles y el precio oscila entre los $0.01 y los $ 500.
- No se puede eliminar un producto, cliente o vendedor que se encuentre relacionado con una venta.
- En caso de modificar el código de un producto, este no puede estar asociado a otro producto.
- En caso de modificar el código de una venta, este no puede estar asociado a otra venta.
- En caso de modificar la identificación de un cliente, esta no puede estar asociado a otro cliente.
- En caso de modificar la identificación de un vendedor, esta no puede estar asociado a otro vendedor.
- Una venta no puede solicitar más unidades de las que un producto tiene disponible.
- Una venta solo cuenta con dos estados: PENDIENTE y APROBADO.
- Al registrar una venta, se disminuirán la candtidad de unidades disponibles al producto vendido según la cantidad solicitada en la venta (es posible que el producto se modifique hasta tener una cantidad de cero unidades disponibles).
- Una venta es registrada con estado PENDIENTE.
- Solo es posible aprobar ventas que se encuentren en estado PENDIENTE.

Nota: Algunas de estas reglas se entuentran definidas en el archivo 'constants.properties' (ubicado en el directorio 'src/main/resources')

## Respuestas

- Mensaje: los mensajes de respuesta pueden variar según las reglas de negocio. Están configurados en el archivo 'messages.properties' (ubicado en el directorio 'src/main/resources')
- Código: En caso de que una operación sea exitosa el código de respuesta es 200. En caso contrario el código de respuesta es 500.
