-- V1__Create_tables.sql
CREATE TABLE stock (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    category TEXT
);

CREATE TABLE category (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    products TEXT
);

CREATE TABLE product (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DOUBLE,
    quantity INT,
    displayOrder INT,
    starQuantity INT
);
