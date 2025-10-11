# admin-service

Admin (master data) microservice for managing BusRoute records.

## Build

mvn clean package

## Run

java -jar target/admin-service-0.0.1-SNAPSHOT.jar

## Docker

docker build -t admin-service:0.0.1 .
docker run -p 8101:8101 --env SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/admin_db admin-service:0.0.1

## API Curls

Post Route
```
curl -X 'POST' \
  'http://localhost:8101/api/admin/routes' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "busNumber": "OD000211",
  "source": "BHUBANESWAR",
  "destination": "KOLKATA",
  "price": 500,
  "totalSeats": 50
}'

```

Get Routes

```
curl -X 'GET' \
  'http://localhost:8101/api/admin/routes' \
  -H 'accept: */*'
  
  reponse:
  
  [
  {
    "busNumber": "KA000211",
    "source": "Bengaluru",
    "destination": "Hyderabad",
    "price": 1500,
    "totalSeats": 60
  },
  {
    "busNumber": "OD000211",
    "source": "BHUBANESWAR",
    "destination": "KOLKATA",
    "price": 500,
    "totalSeats": 50
  }
]

```