package com.stock.flex.resource.request;

import com.stock.flex.entity.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProductRequest(
        @NotBlank(message = "{nome.obrigatorio}")
        String name,
        String description,
        double price,
        int quantity,
        int displayOrder,
        @Pattern(regexp = "\\d{0,5}", message = "Deve conter um numero de 0 a 5")
        int starQuantity) {

    public ProductRequest(ProductEntity product) {
        this(product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getDisplayOrder(), product.getStarQuantity());
    }

}