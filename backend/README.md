# CarManager Backend

## Project Description

CarManager is a fleet management backend application. Start the application by executing `com.carManager.CarManagerApplication`, which starts a webserver on port 8080 (http://localhost:8080).

## Technologies

* Java 21
* Spring Boot 3.2.x
* Database H2 (In-Memory)
* Maven

## Architecture

* **DataTransferObjects**: Objects used for outside communication via the API
* **Controller**: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs
* **Service**: Implements the business logic and handles the access to the DataAccessObjects
* **DataAccessObjects**: Interface for the database. Inserts, updates, deletes and reads objects from the database
* **DomainObjects**: Functional Objects which might be persisted in the database





