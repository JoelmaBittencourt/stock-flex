package com.stock.flex.resource.response;

import com.stock.flex.entity.CategoryEntity;

import java.util.List;
import java.util.UUID;

public record CategoryResponse(UUID id,
                               String name,
                               String description,
                               List<String> products) {

    public CategoryResponse(CategoryEntity category) {
        this(category.getId(), category.getName(), category.getDescription(), category.getProducts());
    }
}
