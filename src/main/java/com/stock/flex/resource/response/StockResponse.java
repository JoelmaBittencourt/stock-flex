package com.stock.flex.resource.response;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.StockEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record StockResponse(UUID id,
                            String name,
                            String description,
                            List<CategoryResponse> categories) {

    public StockResponse(StockEntity stock) {
        this(stock.getId(), stock.getName(), stock.getDescription(), mapCategoriesToResponses(stock.getCategory()));
    }

    private static List<CategoryResponse> mapCategoriesToResponses(List<CategoryEntity> categories) {
        return categories.stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }
}
