package com.stock.flex.resource.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record StockRequest(@NotBlank(message = "Nome é obrigatório")String name,
                           String description,
                           List<CategoryRequest> category) {
}


