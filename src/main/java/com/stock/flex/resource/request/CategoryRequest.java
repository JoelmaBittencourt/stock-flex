package com.stock.flex.resource.request;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public record CategoryRequest(String name,
                              String description,
                              List<ProductRequest> products) {

    public CategoryRequest(CategoryEntity category) {
        this(category.getName(), category.getDescription(), mapProductsToRequests(category.getProducts()));
    }

    private static List<ProductRequest> mapProductsToRequests(List<ProductEntity> products) {
        return products.stream()
                .map(ProductRequest::new)
                .collect(Collectors.toList());
    }
}