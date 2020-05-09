# REST Test

## Introducción

API Rest de prueba para el cargo de Líder Técnico en [Fexibility](https://www.flexibility.com.ar/).

[Fexibility](https://www.flexibility.com.ar/) Shopping Cart es una API REST que permite a vendedores y clientes 
gestionar sus comprar de forma online. Es posible listar los productos, ordenes y carros de compras en la base de datos.

NOTA: Esta aplicación no está completada ya que es un challenge ténico en un proceso de selección por lo que no cuenta
con todas las características necesarias para ser considerada "production-ready". Por ejemplo, la aplicación no tiene
un modulo de autorización/autenticación, esto fue una decisión de diseño para concentrarme en la lógica de negocios.

## Stack

La aplicación utiliza las siguientes tecnologías:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Hibernate](http://hibernate.org/)
- [Swagger](https://swagger.io/)
- [Mockito](https://site.mockito.org/)
- [JUnit](https://junit.org/junit5/)
- [Docker](https://www.docker.com/)

## Endpoints

La lista de endpoints puede ser consultada en [Swagger](https://frozen-chamber-56289.herokuapp.com/swagger-ui.html#/).

La aplicación se encuentra en Heroku y puede ser consultada.

Así mismo, se puede utilizar la siguiente colección de [Postman](https://www.postman.com/):

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/9a756f5ea52c0d97d991)

## Testing

La API cuenta con pruebas unitarias para la capa de servicios.
