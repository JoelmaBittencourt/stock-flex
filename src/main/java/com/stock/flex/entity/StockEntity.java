package com.stock.flex.entity;

import com.stock.flex.resource.request.CategoryRequest;
import com.stock.flex.resource.request.StockRequest;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
    private List<CategoryEntity> category;

    public StockEntity(StockRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.category = mapCategoryRequestsToEntities(request.category());
    }

    public void updateInfo(StockRequest request) {
        if (request.name() != null) {
            this.name = request.name();
        }
        if (request.description() != null) {
            this.description = request.description();
        }
        if (request.category() != null) {
        this.category = mapCategoryRequestsToEntities(request.category());
        }
    }

    private List<CategoryEntity> mapCategoryRequestsToEntities(List<CategoryRequest> categoryRequests) {
        return categoryRequests.stream()
                .map(CategoryEntity::new)
                .collect(Collectors.toList());
    }

}
