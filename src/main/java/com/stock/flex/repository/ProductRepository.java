package com.stock.flex.repository;

import com.stock.flex.entity.Person;
import com.stock.flex.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findByCategoryStockUser(Person userSpringSecurity);
}
