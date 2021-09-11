# SOLUCIÓN

Se desarrolló y complementó el API REST con los servicios solicitados, además, se desplegó el API en un servidor de Heroku gratuito junto con la base de datos para pruebas.

Documentación Técnica de API Rest: https://examensrl.herokuapp.com/payments/swagger-ui.html

Pruebas a Controladores REST: https://examensrl.herokuapp.com/payments/swagger-ui.html

URL(Context Path) de entrada API REST: https://examensrl.herokuapp.com/payments/

![image](https://user-images.githubusercontent.com/78576954/132962908-8213783b-4642-4cec-b01d-6799fbbc15a2.png)


# API REST

Se agregaron los nuevos servicios propuestos al API REST, a continuación se describen los controladores REST junto con las peticiones HTTP que se encargan de consumir los servicios desarrollados:

product Controller:

![image](https://user-images.githubusercontent.com/78576954/132962969-b68bf4de-9df8-405e-aa95-b88d1c0eedc8.png)

purchaseTransaction Controller:

![image](https://user-images.githubusercontent.com/78576954/132962977-ad94f009-193f-465b-b5bc-ccc6aedec740.png)

client Controller:

![image](https://user-images.githubusercontent.com/78576954/132962982-f7d277cd-5203-4325-8fd1-e585c7c195cc.png)

Seller Controller:

![image](https://user-images.githubusercontent.com/78576954/132962993-0f41141f-010a-4538-addd-f64288485bbc.png)


La parte Backend de la aplicación se desarrolló usando Java 1.8, Spring Boot, Spring Data JPA para modelar capa de datos y sus respectivos repositorios, Spring Web para exponer los servicios de tipo Rest para ser consumidos, y Maven como gestor de dependencias.

Como se puede observar en la siguiente Imagen, el Backend de la aplicación fue desarrollado teniendo en cuenta una estructura de paquetes, que incluyen Modelos, Controladores, Servicios y Repositorios.

![image](https://user-images.githubusercontent.com/78576954/132963119-dd31e436-dde6-4ce8-a2a4-91783afc7a2f.png)

Controllers: Controladores de tipo REST que expone los servicios de la aplicación, recibe petición http.
Services: Servicios de la aplicación, en él se aloja la lógica de negocio de la misma, haciendo uso de los repositorios o capa de acceso a datos.
Models: Clases que representan entidades de tablas de BD. (clients, sellers, products, purchaseTransactions)
Repositories: Interfaces que tienen como fin el mapeo de la capa de datos de cada una de las entidades descritas en los modelos.

# Base de Datos

A su vez que el API REST, también se desplegó la base de datos en un servidor gratuito de Heroku. De esta forma se procedió a desarrollar la capa de datos, se diseñó una base de datos en PostgreSQL, se plantea el modelo entidad relación mostrado en la siguiente Imagen, donde es posible observar cuatro tablas, purchaseTransactions, clients, products, sellers con sus respectivos campos, llaves primarias y relaciones de llaves foráneas:

![image](https://user-images.githubusercontent.com/78576954/132962679-d8954962-ee4a-47a9-b59c-c2b669922f9b.png)


# PRUEBA DE FUNCIONAMIENTO:

Teniendo en cuenta la url context path del api https://examensrl.herokuapp.com/payments/, su funcionamiento se puede comprobar por medio de la UI de Swagger, o en su defecto por medio de un cliente de peticiones HHTP (POSTMAN, Thunderclient, navegador(GET), etc)

Ejemplo: Al hacer la siguiente petición GET en el navegador se obtiene la lista de Transacciones de compra existentes en la base de datos:

https://examensrl.herokuapp.com/payments/purchaseTransactions/getAll

![image](https://user-images.githubusercontent.com/78576954/132963736-7d9a799b-03c9-44e7-9f9a-83e9da71dea6.png)


DEMÁS PETICIONES GET IMPLEMENTADAS:

clients:
https://examensrl.herokuapp.com/payments/clients/getAll

products:
https://examensrl.herokuapp.com/payments/products/getAll

sellers: 
https://examensrl.herokuapp.com/payments/sellers/getAll


Ejemplo: Al hacer la siguiente petición POST en un cliente HTTP, se puede crear un nuevo cliente:

clients:
https://examensrl.herokuapp.com/payments/clients/save

body:
{
    "identification":"1010243260",
    "name": "Brayan",
    "email": "fernando.cardenascdn@gmail.com"
}


![image](https://user-images.githubusercontent.com/78576954/132964233-caa8572a-3777-4f1d-a690-baab2e2ab935.png)


DEMÁS PETICIONES POST IMPLEMENTADAS:

purchase Transactions:
https://examensrl.herokuapp.com/payments/purchaseTransactions/save

{
	"total": 12000,
	"approval": false,
	"idclient": "1010243260",
	"idproduct": 1,
	"idseller": 1
}

products:
https://examensrl.herokuapp.com/payments/products/save

{
    "name":"maleta",
	"price": 12000
}

seller:
https://examensrl.herokuapp.com/payments/sellers/save

{
    "identification":"1010243260",
    "name": "Nombre Vendedor",
    "email": "fernando.cardenascdn@gmail.com"
}



Además de las peticiones mostradas, también se implementaron peticiones de tipo PUT(Actualizar) y DELETE(Eliminar), con el objetivo de cumplir con las operaciones requeridas del CRUD. Estas pueden ser consultadas en la documentación del API: https://examensrl.herokuapp.com/payments/swagger-ui.html

