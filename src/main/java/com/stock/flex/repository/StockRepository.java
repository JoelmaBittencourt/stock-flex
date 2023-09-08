package com.stock.flex.repository;

import com.stock.flex.entity.PersonEntity;
import com.stock.flex.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, UUID> {

    List<StockEntity> findByUser(PersonEntity user);
}
