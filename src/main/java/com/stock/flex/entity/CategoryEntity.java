package com.stock.flex.entity;


import com.stock.flex.resource.request.CategoryRequest;

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
    private List<String> products;

    public CategoryEntity(CategoryRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.products = request.products();
    }

    public void updateInfo(CategoryRequest response) {
        if (response.name() != null) {
            this.name = response.name();
        }
        if (response.description() != null) {
            this.description = response.description();
        }
        if (response.products() != null) {
            this.products = response.products();
        }
    }
}
