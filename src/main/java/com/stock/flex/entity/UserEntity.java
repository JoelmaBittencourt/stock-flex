package com.stock.flex.entity;

import com.stock.flex.resource.request.StockRequest;
import com.stock.flex.resource.request.UserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;
    private String password;

    @OneToMany
    @JoinColumn(name = "users_id")
    private List<StockEntity> stocks;

    public UserEntity(UserRequest request) {
        this.username = request.username();
        this.password = request.password();
    }

    public void updateInfo(UserRequest request) {
        if (request.username() != null) {
            this.username = request.username();
        }
        if (request.password() != null) {
            this.password = request.password();
        }
    }

    private List<StockEntity> mapStockRequestsToEntities(List<StockRequest> categoryRequests) {
        return categoryRequests.stream()
                .map(StockEntity::new)
                .collect(Collectors.toList());
    }
}
