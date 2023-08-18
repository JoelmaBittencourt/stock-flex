package com.stock.flex.entity;


import com.stock.flex.resource.request.CategoryRequest;
import com.stock.flex.resource.request.ProductRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockEntity stock;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<ProductEntity> products;

    public CategoryEntity(CategoryRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.products = mapProductRequestsToEntities(request.products());
    }

    public void updateInfo(CategoryRequest request) {
        if (request.name() != null) {
            this.name = request.name();
        }
        if (request.description() != null) {
            this.description = request.description();
        }
        this.products = mapProductRequestsToEntities(request.products());
    }

    private List<ProductEntity> mapProductRequestsToEntities(List<ProductRequest> productRequests) {
        return productRequests.stream()
                .map(ProductEntity::new)
                .collect(Collectors.toList());
    }
}
