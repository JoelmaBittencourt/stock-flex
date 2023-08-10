package com.stock.flex.entity;

import com.stock.flex.resource.request.StockRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private List<String> category;

    public StockEntity(StockRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.category = new ArrayList<String>();
    }

}
