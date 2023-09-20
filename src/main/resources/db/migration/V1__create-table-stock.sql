CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(60) NOT NULL
);

CREATE TABLE stock (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    user_id BINARY(16),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE category (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    stock_id BINARY(16),
    FOREIGN KEY (stock_id) REFERENCES stock (id)
);

CREATE TABLE product (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DOUBLE,
    quantity INT,
    displayOrder INT,
    starQuantity INT,
    category_id BINARY(16),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE user_stock (
    user_id BINARY(16) NOT NULL,
    stock_id BINARY(16) NOT NULL,
    PRIMARY KEY (user_id, stock_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (stock_id) REFERENCES stock (id)
);
