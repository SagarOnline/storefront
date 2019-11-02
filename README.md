# StoreFront Application Domain Model

This is a domain model implementation for a simple Checkout Counter in an retail shopping store. The application name is Store Front.

#### Problem Statement
Build the domain model only for a checkout counter in an online retail store that scans products and
generates an itemized bill.
The bill should also total the cost of all the products and the applicable sales tax for each product.
The total cost and total sales tax should be printed
Sales tax varies based on the type of products
- Category A products carry a levy of 10%
- Category B products carry a levy of 20%
- Category C products carry no levy
## Getting Started

The domain model library can be packaged as a library and used in application.

### Prerequisites

This library is developed using
* Java 8
* Spring Boot 2.2.0
* Maven


### Installing

Regular maven commands can be used to compile, test and package library.

## Domain Model Design Overview

Design considerations for the domain model implementation are explained in below section.

### Domain Driven Design

DDD concepts are applied while designing a domain model for **Checkout Counter** bounded context. 
The Tactical Design of a domain model is explained in [Concepts](#Concepts) and [Domain Layer](#Domain-Layer) section below.

The implementation is a domain layer in an Onion Architecture. 
Application layer can be built on top of this layer to expose Store Front fuctionality as a Facade.
The domain model implementation is independent of any infrastructure dependencies such as database or external service endpoints.



### Concepts

The domain model is implemented around below domain concepts.

#### Store Front
This is a name of Point of Sale (POS) application used by Online Store to carry out retail transaction.

#### Shopping Counter
It is a POS counter setup in Retail Store. Store can have more than one shopping counter. 
All purchase transactions are carried out at a Shopping Counter. 

#### Shopping Cart & Cart Item
Shopping Cart represents the purchases made by a customer. 
All the items added in a Shopping Cart are called Cart Items.  

#### Checkout & PurchaseOrder
Shopping Cart can be checked out to proceed towards a payment. 
Before making a payment, shopping counter shows a Purchase Order for the items added in a shopping cart. 
Purchase Order shows the itemized cost for each Order Item which includes a sales tax. 
It also shows the total cost of an order.

#### Payment & Invoice
Upon making a payment, Shopping Counter shows an Invoice which is noting but a bill. 
Bill shows the itemized cost, total cost, tax details and payment details.

## Domain Layer Implementation

This section have implementation details of the domain model

#### Aggregates
Examples of Aggregates in this implementation are ```StoreFrontAggreage```,  ```ShoppingCounterAggreage```,```ShoppingCartAggreage```.
These aggregates encapsulate the operations on aggregate roots. For e.g. ```ShoppingCartAggreage.checkout()``` operation checks out a shopping cart represented by that aggregate instance.
Thus it runs the validations required, generates a PurchaseOrder entity and updates a purchase order reference in ShoppingCart entity.
Aggregates also mark transactionsl boundry around opetations.

#### Repositories
Examples of Repositories in this implementation are ```PurchaseOrderRepository```,  ```ProductRepository```,```ShoppingCartRepository```.
Repositories abstracts the infrastructure implementation to domain layer thus making it independent of infrastructure. It is a responsibility of out layers to provide repository implementation.

#### Command and Queries
The domain layer also have Command and Query classes which represent the Update and Read oprations on domain model respectively.
For e.g. ```CheckoutShoppingCartCommand``` represents a request for checking out a shopping cart. It has ```shoppingCartId``` field.
It is a responsibility of Application Layer (layer above domain layer in Onion Architecture) to define a handler (say ```CheckoutShoppingCartHandler```)  Handler class will use the domain layer to carry out require operation.

#### Tests
Naming conventions for 

* Integration Tests ->  *****IntegrationTest.java 
* Unit Tests -> *****Test.java 


## Examples

#### Setup a Shopping Counter

Below code snippet shows an example to setup a Shopping Counter in store.
```
@Autowired
StoreFrontService storeFrontService;

...
...
    
StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
storeFront.setupNewShoppingCounter("First Counter");

```

#### Add Items into Shopping Cart

Below code snippet shows an example to add an item to Shopping Cart .
```
@Autowired
StoreFrontService storeFrontService;

...
...

// start a shopping cart for customer
ShoppingCartAggregate shoppingCartAggregate =
                storeFrontService.getStoreFront()
                        .getShoppingCounter(SHOPPING_COUNTER_NAME).get()
                        .startShoppingCart(CUSTOMER_NAME);
                        
Long productId = ............ // get product Id
BigDecimal orderQuantity = ........
shoppingCartAggregate.addToCart(productId, orderQuantity);

```