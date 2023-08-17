package com.stock.flex.entity;

import com.stock.flex.resource.request.StockRequest;
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
        this.category = request.category();
    }

    public void updateInfo(StockRequest response) {
        if (response.name() != null) {
            this.name = response.name();
        }
        if (response.description() != null) {
            this.description = response.description();
        }
        if (response.category() != null) {
        this.category = response.category();}
    }
}
