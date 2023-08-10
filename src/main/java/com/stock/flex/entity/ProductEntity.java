package com.stock.flex.entity;

import com.stock.flex.resource.request.ProductRequest;
import com.stock.flex.resource.response.ProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public ProductEntity(ProductRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.price = request.price();
        this.quantity = request.quantity();
        this.displayOrder = request.displayOrder();
        this.starQuantity = request.starQuantity();

    }

    public void updateInfo(ProductResponse response) {
        if (response.id() != null) {
            this.id = response.id();
        }
        if (response.name() != null) {
            this.name = response.name();
        }
        if (response.description() != null) {
            this.description = response.description();
        }
    }
}
