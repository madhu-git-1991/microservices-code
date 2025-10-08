CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;
CREATE TABLE IF NOT EXISTS bus_inventory (
  bus_number VARCHAR(255) PRIMARY KEY,
  available_seats INT,
  last_updated TIMESTAMP
);
