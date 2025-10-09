# Course Management Microservice

This is a Spring Boot microservice for managing courses (part of a school management system).

## Features
- CRUD operations for Course
- H2 in-memory database
- REST endpoints under `/api/courses`
- Dockerfile included

## Run locally
Requires Java 17 and Maven.

```bash
cd course-service
mvn spring-boot:run
```

H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:coursedb`)

## Build jar
```bash
mvn clean package
java -jar target/course-service-0.0.1-SNAPSHOT.jar
```

## Docker
Build:
```bash
docker build -t course-service:latest .
```

Run:
```bash
docker run -p 8080:8080 course-service:latest
```
