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



## 1. Rutas de la api.


### 1.1 Cliente
```
(GET)		/clients
```
``` 
(POST)		/clients
```
```
(PUT)		/clients 
```
```
(DELETE)	/clients/:id 
```
```
(GET)		/:id/purcharses 
```
```
(PUT)		/:idCliente/transactions/:idPurcharse/authorize
```

### 1.2 Producto
```
(GET)		/products 
```
```
(POST)		/products 
```
```
(PUT)		/products 
```
```
(DELETE)	/products/:id 
```


### 1.3 Compra
```
(GET)		/purcharses 
```
```
(POST)		/purcharses 
```
```
(PUT)		/purcharses 
```
```
(DELETE)	/purcharses/:id 
```


### 1.4 Transacción
```
(GET)	 	/transactions
```
```
(PUT)	 	/purcharse 
```
```
(DELETE) 	/transactions/:id 
```


## 2. Modelos

### 2.1 Client
```
{
	id: Long,
	name: String,
	surname: String
	purcharses: List<Purcharse>
}
```

### 2.2 Product
```
{
	id: Long,
	name: String
	price: BigDecimal
}
```

### 2.3 Purcharse
```
{
	id: Long,
	products: List<Product>
	transaction: List<Transaction>
	cost: BigDecimal
	status: PurcharseEnum
}
```

### 2.4 Transaction
```
{
	id: Long,
	transactionTime: LocalDateTime
}
```

### 2.5 PurcharseEnum
```
{
	APPROVED,
	REJECTED
	PENDING
}
```