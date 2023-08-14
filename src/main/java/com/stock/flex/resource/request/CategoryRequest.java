package com.stock.flex.resource.request;

import com.stock.flex.entity.CategoryEntity;

import java.util.List;

public record CategoryRequest(String name,
                              String description,
                              List<String> products) {

    public CategoryRequest(CategoryEntity category) {
        this(category.getName(), category.getDescription(), category.getProducts());
    }
}