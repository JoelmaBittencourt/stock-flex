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


    @ManyToOne // Estabelece o relacionamento muitos-para-um com a categoria
    @JoinColumn(name = "category_id") // Especifique a coluna de junção
    private CategoryEntity category; // Adicione esse atributo


    public ProductEntity(ProductRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.price = request.price();
        this.quantity = request.quantity();
        this.displayOrder = request.displayOrder();
        this.starQuantity = request.starQuantity();

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
