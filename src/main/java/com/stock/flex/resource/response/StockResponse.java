package com.stock.flex.resource.response;

import com.stock.flex.entity.StockEntity;

import java.util.List;
import java.util.UUID;

public record StockResponse(UUID id,
                            String name,
                            String description,
                            List<String>category) {

    public StockResponse(StockEntity stock) {
        this(stock.getId(), stock.getName(), stock.getDescription(), stock.getCategory());
    }
}