package com.stock.flex.resource.request;

import com.stock.flex.entity.ProductEntity;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record ProductRequest(
        String name,
        String description,
        double price,
        int quantity,
        int displayOrder,
        @Pattern(regexp = "\\d{0,5}", message = "Deve conter um número de 0 a 5")
        int starQuantity,
        Date expirationDate,
        Date purchaseDate,

        String brand,
        String purchaseLocation,
        boolean hasCoupon
) {
    public ProductRequest(ProductEntity product) {
        this(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getDisplayOrder(),
                product.getStarQuantity(),
                product.getExpirationDate(),
                product.getPurchaseDate(),
                product.getBrand(),
                product.getPurchaseLocation(),
                product.isHasCoupon()
        );
    }
}
