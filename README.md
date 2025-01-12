# Awesome Pizza API

## Project Description
"Awesome Pizza" is a Spring Boot API that enables customers to place orders for pizzas without registration. The pizzaiolo (pizza maker) can view the pizzaOrder queue and process them one at a time. Customers receive an pizzaOrder code to track their pizzaOrder status until completion.

## Technologies Used
- Java 17+
- Spring Boot
- Maven
- H2 In-Memory Database
- Mockito (for unit testing)

## Getting Started

### Prerequisites
- JDK 17 or higher
- Maven

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/awesome-pizza-api.git
   ```
2. Navigate to the project directory:
   ```bash
   cd awesome-pizza-api
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Database Configuration
The application uses an H2 in-memory database for development and testing purposes. The configuration is set in the `application.properties` file:

**src/main/resources/application.properties**
```properties
spring.datasource.url=jdbc:h2:mem:awesomepizza
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

To access the H2 console, navigate to:
```
http://localhost:8080/h2-console
```
Use the following credentials:
- **JDBC URL**: `jdbc:h2:mem:awesomepizza`
- **Username**: `sa`
- **Password**: `password`

## Running Tests
To run the unit tests, execute the following command:
```bash
mvn test
```

## Project Structure
- `OrderController`: Handles API requests for pizza orders.
- `OrderService`: Contains the business logic for managing orders.
- `OrderRepository`: Interfaces with the in-memory database.
- `Order`: Represents the pizzaOrder entity.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Author
Federico Parezzan
