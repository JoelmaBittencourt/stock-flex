package com.stock.flex.resource.response;

import com.stock.flex.entity.ProductEntity;

import java.util.UUID;

public record ProductResponse(UUID id,
                              String name,
                              String description,
                              double price,
                              int quantity,
                              int displayOrder,
                              int starQuantity) {


    public ProductResponse(ProductEntity product) {
        this(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity(), product.getDisplayOrder(),
                 product.getStarQuantity());
    }

}
