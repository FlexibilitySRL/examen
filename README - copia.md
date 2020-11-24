# REST Solucion

## Descripción del desarrollo
En el desarrollo se aplicaron soluciones que tal vez quepan destacar:
- un archivo de constantes, con parametrización de strings, para mensajes
- un servicio "util" para centralizar validaciones (permite validar todas las entidades)
- un custom converter para mapear entidades a dto y viceversa (o listas de estos)
- distintos tipos de parámetros a los Rest Api (PathVariable, RequestBody, RequestParam)
- creación de custom exceptions
- specifications de JPA en un servicio
- querys jpql en algunos repositorios
- patrón builder

El uso "no óptimo" de alguna de estas soluciones estuvo contemplado y fué intencional. Se aplicaron solamente a fines de proveer distintos tipos de soluciones a un mismo problema.


##### El modelo consiste en la siguiente lógica de negocio:
- Una transacción consiste en una lista de productos (cada uno con su cantidad vendida), solicitada por un cliente y realizada por un vendedor
- El cliente, vendedor y productos vinculados a la transacción deben existir con antelación. Se proveen servicios para el alta, baja y modificación de cada uno.
- Una transacción se crea inicialmente con estado PENDING. Esta puede pasar a los estados APPROVED o REJECTED.
- Se provee un servicio para calcular el monto total de la transacción

#### Documentación de servicios
Se incluye el @javadoc accesible desde [aquí](./documentation/index.html).
En el mismo se presenta una breve descripción de los servicios implementados.
##### Responses
Se describen los responses esperados por cada tipo de servicio:
- GET ---> HttpStatus.OK
- POST ---> HttpStatus.CREATED
- PUT ---> HttpStatus.ACCEPTED
- DELETE ---> HttpStatus.NO_CONTENT


#### Pruebas unitarias (base dato en memoria) y cobertura
Para las pruebas unitarias se utilizó JUnit, y las mismas se ejecutan contra una base en memoria H2.
El Test Coverage se realizó con Jacoco y el informe detallado del mismo se encuentra es accesible desde [aquí](./target/site/jacoco/index.html).

#### Servicios provistos
##### Cliente
    - listClients: Lista la totalidad de los clientes
    - findById: Retorna un cliente específico a partir de su código de cliente (id)
    - findByName: Retorna una lista de clientes cuyos nombres cumplan con el filtro provisto. Case insensitive.
    - save: Persiste un nuevo cliente
    - deleteById: Elimina un cliente a partir de su código de cliente (id)
    - update: Actualiza un cliente a partir de su código de cliente (id)

##### Vendedor
    - listSellers: Lista la totalidad de los vendedores
    - findById: Retorna un vendedor específico a partir de su código de vendedor (id)
    - findByName: Retorna una lista de vendedores cuyos nombres cumplan con el filtro provisto. Case insensitive.
    - save: Persiste un nuevo vendedor
    - deleteById: Elimina un vendedor a partir de su código de vendedor (id)
    - update: Actualiza un vendedor a partir de su código de vendedor (id)

##### Producto
    - listProducts: Lista la totalidad de los productos disponibles
    - findById: Retorna un producto específico a partir de su código de producto (id)
    - findByName: Retorna una lista de productos cuyos nombres cumplan con el filtro provisto. Case insensitive.
    - save: Persiste un nuevo producto
    - deleteById: Elimina un producto a partir de su código de producto (id)
    - update: Actualiza un producto a partir de su código de producto (id)

##### Transacción
    - listTransactions: Lista la totalidad de las transacciones realizadas
    - findByFilters: Retorna una lista de transacciones cuyos atributos cumplan con el filtro provisto.
    - save: Persiste una nueva transacción
    - deleteById: Elimina una transacción a partir de su código de transacción (id)
    - updateTransactionStatusById: Actualiza el estado de la transacción. Valores aceptados (PENDING, APPROVED, REJECTED)
    - totalAmountByTransactionId: Calcula el monto total de la transacción a partir de su código de transacción (id)
