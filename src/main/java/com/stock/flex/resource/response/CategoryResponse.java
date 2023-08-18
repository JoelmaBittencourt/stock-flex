package com.stock.flex.resource.response;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.ProductEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record CategoryResponse(UUID id,
                               String name,
                               String description,
                               List<ProductResponse> products) {

    public CategoryResponse(CategoryEntity category) {
        this(category.getId(), category.getName(), category.getDescription(), mapProductsToResponses(category.getProducts()));
    }

    private static List<ProductResponse> mapProductsToResponses(List<ProductEntity> products) {
        return products.stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }
}
