High Level Design (brief) for Bus Reservation Microservices:

- Service Discovery: Eureka server (this project) for registering microservices.
- Configuration: Centralized config server (not included here) for externalized configs.
- Messaging: JMS broker (ActiveMQ) for saga events and choreography between booking/payment/inventory.
- Databases: MySQL for Booking, Payment, Admin; Inventory uses transaction table.
- Security: OAuth2 (jdbc) for customer auth; secure service-to-service calls with JWT or mTLS.
- Resilience: Use circuit breakers, retries, and idempotent endpoints.
- Deployment: Docker images (Dockerfile included). Use Kubernetes for scaling.
