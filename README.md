# REST Test

# REST service endpoints documentation

## /customer
Under this path was implemented the Customers CRUD 

### GET /customer/{customerId}
Returns:
**HTTP200 OK** The Customer was found
Response Body:
`{
    "id": *customerId*
    "name": *Customers name. Single line.*
}`

**HTTP404 NOT FOUND** The Customer wasn't found

### DELETE /customer/{customerId}
Returns:
**HTTP200 OK** Customer succesfully deleted
**HTTP404 NOT FOUND** Customer wasn't found

### PUT /customer
Creates a new customer
Request Body:
`{
    "name": *Customers name. Single line.*
}`

Returns:
**HTTP201 CREATED** if successully createrd
Response body:
`{
    "id": *customerId*
    "name": *Customers name. Single line.*
}`

**HTTP400 BAD REQUEST** if the received JSON is malformed
Response body empty

## /product
Under this path was implemented the Products CRUD 

### GET /product/{productId}
Returns:
**HTTP200 OK** The Product was found
Response Body:
`{
    "id": *productId*
    "name": *Product name.*
}`

**HTTP404 NOT FOUND** The Customer wasn't found

### DELETE /product/{productId}
Returns:
**HTTP200 OK** Product succesfully deleted
**HTTP404 NOT FOUND** Product wasn't found

### PUT /product
Creates a new product
Request Body:
`{
    "name": *Products name. Single line.*
}`

Returns:
**HTTP201 CREATED** if successully createrd
Response body:
`{
    "id": *productId*
    "name": *Product name. Single line.*
}`

**HTTP400 BAD REQUEST** if the received JSON is malformed
Response body empty



## /purchase
Under this path was implemented the Products CRUD 

### GET /purchase/{customerId}/{productId}
Gets the purchase orders list for the given clientId and productId
Returns:
**HTTP200 OK** The Product was found
Response Body:
`
[
    {
        "id": {purchaseID},
        "product": {
            "id": {productID},
            "name": Product Name
        },
        "customer": {
            "id": {customerId},
            "name": Customer Name
        },
        "aporoved": whether is already approved or not
    },
...
]
`
This list may be empty if no purchases are already placed for that customer and product

### PUT /purchase/{customerId}/{productId}
Creates a new purchase order for the given customer and product IDs.
The order will be still unapproved
request body: empty

Returns:
**200OK** If the purchase order
`{
    "id": {purchaseID},
    "product": {
        "id": {productID},
        "name": Product Name
    },
    "customer": {
        "id": {customerId},
        "name": Customer Name
    },
    "aporoved": false
}`

**400 BAD REQUEST** if the purchase order can´t be placed for any reason


### POST /approve/{purchaseId}
sets the approved value of the purchase to truw
Request Body: empty
Response:
**200 OK** Purchase succesfully approved
**400 BAD REQUEST** The purchase either does not exists of approval was rejected
