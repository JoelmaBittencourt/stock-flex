package com.stock.flex.repository;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findByStockUser(Person userSpringSecurity);
}
