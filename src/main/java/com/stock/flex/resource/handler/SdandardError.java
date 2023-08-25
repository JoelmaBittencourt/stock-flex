//package com.stock.flex.resource.handler;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//
//record StandardError(Integer status, String error, String message, String path) {
//
//    public StandardError(HttpStatus status, Throwable ex, HttpServletRequest request) {
//        this(status.value(), ex.getClass().getSimpleName(), ex.getMessage(), request.getRequestURI());
//    }
//
//    public StandardError(Integer status, String error, String message, String path) {
//        this.status = status;
//        this.error = error;
//        this.message = message;
//        this.path = path;
//    }
//}
