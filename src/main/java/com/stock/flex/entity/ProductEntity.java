package com.stock.flex.entity;

import com.stock.flex.resource.request.ProductRequest;
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
        this.displayOrder = request.displayOrder();
    }
}
