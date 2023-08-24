package com.stock.flex.resource.handler;

import org.springframework.validation.FieldError;

public record ValidationError(String field, String message) {
    public ValidationError(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
