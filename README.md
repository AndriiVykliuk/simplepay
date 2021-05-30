# simplepay

### This is a test MVC project, based on Spring Boot.
It uses common Spring libraries:
* Data
* Validation
* Web
* WebFlux

### Software requirements
JKD11

### The data layer is based on MySql + Audit file storage.
Current datasource is configured to use DB which is accessible from web.

### Approaches and conventions:
* Presumably, the same as it was meant by Spring
* Except a file storage. (Each transaction is audited as a separate line
in the audit file and has Json format).
  
### The project structure
The project was built from an auto generated Spring Boot template.

Folder structure:
* **config**  beans for Spring config, like Swagger.
* **controller** Rest controllers, exception handlers.
* **exception** Business exception.
* **model** Model and close to model clases:
* * **DTO** Classes which models are consumed and produced by API and audit
* * **entity** Entity (JPA) classes 
* * **repo** Spring JPA Repository interfaces
* * **validation** DTO validators
* **service** Service classes, mappers.
* **util** Utility classes
* 
* **resources**
* * **application.properties** Various properties, like DB Url, audit file path, etc.
    
### Project execution ways:
1. `mvnw spring-boot:run` - starts up the application from CLI
2. Open project from Idea IDE, and execute `SimpleApplication`

When Application is started, WEB UI may be opened by the link:
http://localhost:8080/swagger-ui/ (firstly ensure port 8080 is not in use)

### Endpoints available:
1. POST /api/v1/invoice/ - saves new invoice.
2. GET /api/v1/invoice/{id} - Fetches invoice information by given invoice number
3. GET /api/v1/audit/download - Loads audit file.