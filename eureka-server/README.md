# Eureka Server

This is a Spring Boot Eureka Server project configured for the Bus Reservation microservices architecture.
It provides service discovery for microservices like booking, inventory, payment and admin services.

## Features
- Eureka Server (Spring Cloud Netflix)
- Actuator endpoints enabled
- Ready to build with Maven
- Dockerfile included

## How to run

### Locally (with Maven)
```bash
mvn spring-boot:run
```
Eureka console will be available at http://localhost:8761

### Build jar
```bash
mvn clean package
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar
```

### Docker
```bash
docker build -t eureka-server:latest .
docker run -p 8761:8761 eureka-server:latest
```

