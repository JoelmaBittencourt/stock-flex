package com.stock.flex.resource.request;

import java.util.List;

public record StockRequest(String name,
                           String description,
                           List<CategoryRequest> category) {
}


