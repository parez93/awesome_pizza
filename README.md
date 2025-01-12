# Awesome Pizza API

## Project Description
"Awesome Pizza" is a Spring Boot API that enables customers to place orders for pizzas without registration. The pizzaiolo (pizza maker) can view the pizza Order queue nd process them one at a time.

## Technologies Used
- Java 17+
- Spring Boot
- Maven
- H2 In-Memory Database
- Mockito (for unit testing)

## Getting Started


### Preqresites
- JDK 17 or higher
- Maven


### Installation
1. Clone the repository:


``` bash
git clone https://github.com/parez93/awesome_pizza.git
```
2. Navigate to the project directory:
``` bash
cd awesome_pizza
```
3. Build the project:


``` bash
mvn clean install
```
4. Run the application:
``` bash
mvn spring-boot:run
```

### Database Configuration
The application uses an H2 in-memory database for development and testing purposes. The configuration is set in the `application.properties` file:

``` properties
spring.datasource.url=jdbc:h2:mem:awesomepizza
spring.datasource.driver-class-name=org.h2.Driverspring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

To access the H2 console, navigate to:

``` 
http://localhost:8080/h2-console
```
Use the following credentials:
- JDBC URL: `jdbc:h2:mem:awesomepizza`
-  Username: `sa`
-  Password: `password`


### Running Tests
To run the unit tests, execute the following command:

``` bash
mvn test
```

### Code Coverage
To generate a JaCoCo code coverage report, execute the following commands:

``` bash
mvn clean test
mvn jacoco:report
```

The report will be generated in the `target/site/jacoc`. 
Open the `index.html` file in a web browser to view the coverage report.

### Project Structure
- `src/main/java/com/awesomepizza`:
   - `controller/OrderController.java` : Handles API requests for pizza orders.
   - `service/OrderService.java` : Contains the business logic for managing orders.
   - `repository/OrderRepository.java` : Interfaces with the in-memory database.
   - `model/Order.java` : Represents the pizzaOrder entity.
- `src/test/java/com/awesomepizza` : Unit tests for the application.


### Environment Configuration
[src/main/resources/application.properties]

``` properties
spring.datasource.url=jdbc:h2:mem:awesomepizza
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### GitHub Actions
This project uses GitHub Actions for CI/CD. The GitHub Actions workflow is defined in the `.github/workflows` directory

### License
This project is licensed under the MIT License. See the LICENSE file for details.

### Author
Federico Parezzan
