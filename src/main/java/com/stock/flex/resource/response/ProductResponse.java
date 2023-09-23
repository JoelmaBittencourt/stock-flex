package com.stock.flex.resource.response;

import com.stock.flex.entity.ProductEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record ProductResponse(UUID id,
                              String name,
                              String description,
                              double price,
                              int quantity,
                              int displayOrder,
                              int starQuantity,
                              LocalDate expirationDate,
                              LocalDate purchaseDate,
                              String brand,
                              String purchaseLocation,
                              boolean hasCoupon) {


    public ProductResponse(ProductEntity product) {
        this(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity(), product.getDisplayOrder(),
                 product.getStarQuantity(), product.getExpirationDate(), product.getPurchaseDate(),
                product.getBrand(), product.getPurchaseLocation(),product.isHasCoupon());
    }

}
