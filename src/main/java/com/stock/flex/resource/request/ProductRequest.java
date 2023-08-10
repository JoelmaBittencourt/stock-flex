package com.stock.flex.resource.request;

public record ProductRequest(String name,
                             String description,
                             double price,
                             int quantity,
                             int displayOrder,
                             int starQuantity) {

}