# inventory-service

Inventory microservice to manage bus seat availability.

## Build

mvn clean package

## Run

java -jar target/inventory-service-0.0.1-SNAPSHOT.jar

## Docker

docker build -t inventory-service:0.0.1 .
docker run -p 8103:8103 --env SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/inventory_db inventory-service:0.0.1

## Rest call for creation of inventory

```
curl -X 'POST' \
  'http://localhost:8103/api/inventory' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "busNumber": "KA000211",
  "availableSeats": 30,
  "lastUpdated": "2025-10-08T13:58:40.209Z"
}'

response

{
  "busNumber": "KA000211",
  "availableSeats": 30,
  "lastUpdated": "2025-10-08T14:06:13.976389Z"
}

```

```
Get inventory

curl -X 'GET' \
  'http://localhost:8103/api/inventory/check/OD000211' \
  -H 'accept: */*'
  
response

{
  "lastUpdated": "2025-10-08T14:44:46.826619Z",
  "availableSeats": 0,
  "busNumber": "OD000211"
}
```