package com.stock.flex.usecase;

import com.stock.flex.entity.StockEntity;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.exception.NotFoundException;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.StockRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class StockUseCase {

    @Autowired
    private StockRepository repository;

    @Transactional
    public StockEntity createStock(StockRequest request, UserEntity userSpringSecurity) {
        StockEntity newStock = new StockEntity(request);
        newStock.setUser(userSpringSecurity);
        return repository.save(newStock);
    }

    @Transactional
    public StockEntity getStockById(UUID id, UserEntity userSpringSecurity) throws Exception {
        StockEntity stock = repository.findById(id).orElseThrow(() -> new NotFoundException("Estoque não encontrado"));

        if (!stock.getUser().equals(userSpringSecurity)) {
            throw new Exception("Acesso negado! Você não tem permissão para acessar este estoque.");
        }

        return stock;
    }

    @Transactional
    public List<StockEntity> getAllStocks(UserEntity userSpringSecurity) {
        return repository.findByUser(userSpringSecurity);
    }

    @Transactional
    public StockEntity updateStock(UUID id, StockRequest request, UserEntity userSpringSecurity) throws Exception {
        StockEntity stock = repository.findById(id).orElseThrow(() -> new NotFoundException("Estoque não encontrado"));

        if (!stock.getUser().equals(userSpringSecurity)) {
            throw new Exception("Acesso negado! Você não tem permissão para atualizar este estoque.");
        }

        stock.updateInfo(request);
        return stock;
    }

    @Transactional
    public void deleteStock(UUID id, UserEntity userSpringSecurity) throws Exception {
        StockEntity stock = repository.findById(id).orElseThrow(() -> new NotFoundException("Estoque não encontrado"));

        if (!stock.getUser().equals(userSpringSecurity)) {
            throw new Exception("Acesso negado! Você não tem permissão para excluir este estoque.");
        }

        repository.delete(stock);
    }
}

