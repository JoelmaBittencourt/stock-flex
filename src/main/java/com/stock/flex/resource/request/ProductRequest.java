package com.stock.flex.resource.request;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.ProductEntity;

public record ProductRequest(String name,
                             String description,
                             double price,
                             int quantity,
                             int displayOrder,
                             int starQuantity) {

    public ProductRequest(ProductEntity product){
                          this(product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getDisplayOrder(), product.getStarQuantity());
    }

}