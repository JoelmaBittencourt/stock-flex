ALTER TABLE product ADD (
    expiration_date DATE NOT NULL,
    purchase_date DATE NOT NULL,
    brand VARCHAR(255) NOT NULL,
    purchase_location VARCHAR(255) NOT NULL,
    has_coupon BOOLEAN NOT NULL
);
