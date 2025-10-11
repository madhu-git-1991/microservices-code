# Payment Service (for Bus Reservation System)

This is a standalone **Payment Service** microservice (Spring Boot) that demonstrates:
- Payment REST APIs
- JMS (ActiveMQ) listener to receive booking events (e.g. booking created/pending)
- Publishing payment-processed events to an event queue for Inventory/Booking services
- Uses H2 by default for quick testing; can be switched to MySQL profile for production.

Run locally:
1. mvn clean package
2. docker-compose up --build
3. ActiveMQ admin UI: http://localhost:8161 (default credentials usually admin/admin)

Notes:
- The project includes comments showing where inter-service REST calls (to Booking/Inventory) or direct JMS interactions would happen.
- Payment processing is simulated — no real payment gateway is integrated.
