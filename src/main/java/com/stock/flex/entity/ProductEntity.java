package com.stock.flex.entity;

import com.stock.flex.resource.request.ProductRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int displayOrder;
    private int starQuantity;
    private Date expirationDate;
    private Date purchaseDate;
    private String brand;
    private String purchaseLocation;
    private boolean hasCoupon;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    public ProductEntity(ProductRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.price = request.price();
        this.quantity = request.quantity();
        this.displayOrder = request.displayOrder();
        this.starQuantity = request.starQuantity();
        this.expirationDate = request.expirationDate();
        this.purchaseDate = request.purchaseDate();
        this.brand = request.brand();
        this.purchaseLocation = request.purchaseLocation();
        this.hasCoupon = request.hasCoupon();

    }

    public void updateInfo(ProductRequest request) {
        if (request.name() != null) {
            this.name = request.name();
        }
        if (request.description() != null) {
            this.description = request.description();
        }
    }
}
