ALTER TABLE product
ADD (
    expirationDate DATE,
    purchaseDate DATE,
    brand VARCHAR(255),
    purchaseLocation VARCHAR(255),
    hasCoupon BOOLEAN
);
