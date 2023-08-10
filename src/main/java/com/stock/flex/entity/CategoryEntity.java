package com.stock.flex.entity;


import com.stock.flex.resource.request.CategoryRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}