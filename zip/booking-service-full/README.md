# Booking Service

Spring Boot microservice for managing bus bookings.


# Local setup 
## Mysql 8.0.43
 - create database bookingdb
## Start server

Post booking
```
curl -X 'POST' \
  'http://localhost:8081/api/bookings' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  
  "busNumber": "KA000211",
  "bookingDate": "2025-10-08",
  "source": "HYD",
  "destination": "BLR",
  "numberOfSeats": 1,
  "status": "PENDING",
  "passengers": [
    {
  
      "name": "Cena",
      "age": 40
    },
{
  
      "name": "Rambo",
      "age": 12
    }
  ]
}'
```
create booking response:
```
{
  "bookingNumber": 3,
  "busNumber": "KA000211",
  "bookingDate": "2025-10-08",
  "source": "HYD",
  "destination": "BLR",
  "numberOfSeats": 1,
  "status": "PENDING",
  "passengers": [
    {
      "passengerId": 3,
      "name": "Cena",
      "age": 40,
      "bookingId": 3
    },
    {
      "passengerId": 4,
      "name": "Rambo",
      "age": 12,
      "bookingId": 3
    }
  ]
}
```

get booking & it's response

```
curl -X 'GET' \
  'http://localhost:8081/api/bookings/3' \
  -H 'accept: */*'
  
 response: 
  {
  "bookingNumber": 3,
  "busNumber": "KA000211",
  "bookingDate": "2025-10-08",
  "source": "HYD",
  "destination": "BLR",
  "numberOfSeats": 1,
  "status": "PENDING",
  "passengers": [
    {
      "passengerId": 3,
      "name": "Cena",
      "age": 40,
      "bookingId": 3
    },
    {
      "passengerId": 4,
      "name": "Rambo",
      "age": 12,
      "bookingId": 3
    }
  ]
}

```


## Build and Run

```bash
mvn clean package
docker build -t booking-service .
docker run -p 8081:8081 booking-service
```
