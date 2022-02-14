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
  
4) Documentar brevemente los servicios implementados. http://localhost:8080/payments/swagger-ui/index.html http://localhost:8080/payments/v3/api-docs/
 
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


# API Documentation

You can see and test every endpoint here: /payments/swagger-ui/index.html

or use postman collection (in project folder)

AWS EC2 API example: http://ec2-3-140-242-134.us-east-2.compute.amazonaws.com/payments/clients

SWAGGER: http://ec2-3-140-242-134.us-east-2.compute.amazonaws.com/payments/swagger-ui/index.html


##Clients: 
Endpoints with CRUD operation for clients

    url: /payments/clients


- Get All Clients - GET: /payments/clients

  Response: List of clients or array empty


- Get Client by ID - GET: /payments/clients/{id}
      Path variable "id": Long (required)

  __Response__: 
  - 200 OK - body: found client
  - 404 NOT FOUND


- Create Client - POST: /payments/clients

   __Request body__:
        
   firstName (required)

   lastName (required)
 
  __Response__:
    - 202 CREATED - body: client object
  

- Update Client - PUT: /payments/clients/{id}

  __Request body__:

  firstName (required)

  lastName (required)

  __Response__:
    - 200 OK - body: client object
  

- Delete Client - DELETE: /payments/clients/{id}

  __Response__:
    - 204 NO CONTENT

##Products: 
Endpoints with CRUD operation for products

    url: /payments/products

- Get All Products - GET: /payments/products

  __Response__: 200 OK - List of products or array empty


- Get Product by ID - GET: /payments/products/{id}
  Path variable "id": Long (required)

  __Response__:
    - 200 OK - body: found product
    - 404 NOT FOUND


- Create Product - POST: /payments/products

  __Request body__:

  description (string, required)

  price (decimal, required)

  __Response__:
    - 202 CREATED - body: Product object


- Update Product - PUT: /payments/products/{id}

  __Request body__:

  description (string, required)

  price (decimal, required)

  __Response__:
    - 200 OK - body: Product object


- Delete Product - DELETE: /payments/products/{id}

  __Response__:
    - 204 NO CONTENT
##Vendors: 
Endpoints with CRUD operation for vendors
    
    url: /payments/vendors

- Get All Vendors - GET: /payments/vendors

  __Response__: 200 OK - List of vendors or array empty


- Get Vendor by ID - GET: /payments/vendors/{id}
  Path variable "id": Long (required)

  __Response__:
    - 200 OK - body: found product
    - 404 NOT FOUND


- Create Vendor - POST: /payments/vendors

  __Request body__:

  name (string, required)

  ssn (string, required)

  __Response__:
    - 202 CREATED - body: Vendor object


- Update Vendor - PUT: /payments/vendors/{id}

  __Request body__:

  name (string, required)

  ssn (string, required)

  __Response__:
    - 200 OK - body: Vendor object


- Delete Vendor - DELETE: /payments/vendors/{id}

  __Response__:
    - 204 NO CONTENT
##Transactions: 
Endpoints for creating and confirming transactions

    url: /payments/transactions

- Create Transaction - POST: /payments/transactions
    
    __Request body__: 

    type: BUY/SELL

    clientId: (long, required)

    vendorId: (long, required)

    products: 
    [
     {

    product: (long)
 
    amount: (decimal)
}
       ]

  __Responses__:
    - 202 CREATED - body: transaction object
  

- Confirm transaction - PUT /payments/transactions/{id}/confirm

    ( the transactionStatus is updated to _"approved"_)

  __Responses__:
    - 200 OK - body: transaction object
  