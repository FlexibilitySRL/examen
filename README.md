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



# REST test --- Comentarios acerca de la solución


## Suposiciones de negocio

1) Se asume que el módulo a desarrollar dará servicio a un front web que será utilizado por 
personal de la empresa ACME.

2) Se trata de un sistema de soporte a la venta (CRM/ERP) y no de un portal web tipo ML.

3) La secuencia de pasos que se llevarían a cabo para completar una transacción de 
venta serían los siguientes:


Feature: Compra de productos

    Escenario: Compra presencial de producto de forma electrónica.
        Como cliente, quiero poder acercarme a un local de la firma, y comprar un producto pagando de forma electrónica.

    Dado que:   
    - Una persona ingresa al local de ventas de la empresa.
    - Esta persona recorre el local en busca de un producto de su interés.
    - Si tiene dudas con respecto al producto o desea comprarlo, busca a un representante de ventas.
    - La persona decide efectuar la compra.

    Cuando:
   - El representante de ventas se acerca al terminal de ventas y realiza las siguientes 
     acciones:
         
        - Verifica la existencia del producto en stock y hay cantidad suficiente.
        - Verifica si la persona ya fue dada como cliente. Si no fue así, solicita 
          los datos y da de alta al nuevo cliente en la base.
        - Inicia una nueva transacción de compra, en donde asocia al cliente y al o a 
          los productos solicitados, junto con la información necesaria para procesar 
          el pago.

     Entonces:
        - El medio de pago es electrónico por lo que se procede al cobro contra 
          la entidad.
        - La entidad aprueba el pago


## Propuesta técnica

Se realiza un desarrollo contract first, lo que permite modelar libremente la API REST, 
para luego propagar las decisiones y restricciones de alto nivel a los servicios implementados.


### Especificación OpenAPI OAS3

Se generó y publicó la documentación de la PaymentManagement API que describe las 
operaciones de ABMC sobre las entidades requeridas. La documentación de la misma se 
puede consultar en https://studio-ws.apicur.io/sharing/fe39ccdb-61cc-47f2-a78c-f9c3b1bac9ea 


### Modelo de datos

Se incluye un archivo /doc/Entidades.drawio que muestra un esquema con las entidades 
de dominio desarrolladas y las entidades satélite que podrían estar dando soporte a 
la solución (y deberían implementarse) para efectivamente poder llevar a cabo la transacción de 
compra propuesta en el ejercicio.

#### DTOs
Por simplicidad, desde el punto de vista de controladores/servicios, todas las Entidades 
generadas automáticamente por la API REST se consideran DTOs.


#### Entidades de dominio
Las entidades de dominio se generaron a posteriori en base a las diseñadas durante 
la creación de la API rest.
Para el mapeo entre las entidades de dominio y los DTOs, se utilizó mapstruct. El código 
de mapeo específico se genera bajo el directorio `target/generated-sources/annotations` 
mediante plugin de maven y en base a la interfaz definida `DTOMapper`


### Docker
Se generó desde el primer momento los archivos `docker-compose.yaml` y `Dockerfile` 
para poder conectar al módulo con la base mysql 5 correspondiente.


### Pruebas de integración y funcionales

Se generaron dos colecciones POSTMAN que pueden ser ejecutadas vía `newman` desde línea 
de comandos

#### Prueba de integración

Es una prueba simple que busca recorrer una vez cada método cubierto por la API.


- `./run_integration_tests.sh`: corre sobre el módulo local

#### Prueba funcional

Es una suite de pruebas que busca generar entidades validando que los parámetros creados 
coincidan con los enviados y a su vez ejercita algunos caminos críticos del negocio, 
como garantizar que no se pueda setear dos veces el estado de aprobación de una transacción


- `./run_functional_tests.sh`: corre sobre el módulo local
- `./run_functional_tests_docker.sh`: corre sobre el módulo compilado en la imagen docker



### Cobertura

Se configuró jacoco. La cobertura no es ideal. Se podría mejorar incorporando las pruebas 
funcionales que se realizaron en Postman a Features en gherkin.



### Calidad de código

Se utilizó sonarLint para encontrar defectos y code smells. 









