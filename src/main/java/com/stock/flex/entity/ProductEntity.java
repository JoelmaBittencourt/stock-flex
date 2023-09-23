package com.stock.flex.entity;

import com.stock.flex.resource.request.ProductRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private LocalDate expirationDate;
    LocalDate purchaseDate;
    private String brand;
    private String purchaseLocation;
    private boolean hasCoupon;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public ProductEntity(String name, String description, double price, int quantity, int starQuantity, LocalDate expirationDate, LocalDate purchaseDate, String brand, String purchaseLocation, boolean hasCoupon) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.starQuantity = starQuantity;
        this.expirationDate = expirationDate;
        this.purchaseDate = purchaseDate;
        this.brand = brand;
        this.purchaseLocation = purchaseLocation;
        this.hasCoupon = hasCoupon;
    }

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
