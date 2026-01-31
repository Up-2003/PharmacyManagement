CREATE DATABASE IF NOT EXISTS pharmacy;
USE pharmacy;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE medicine (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    stock INT NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE bill (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    medicine_id BIGINT,
    customer_name VARCHAR(100),
    quantity INT,
    total_amount DOUBLE,
    date DATE,
    FOREIGN KEY (medicine_id) REFERENCES medicine(id)
);
