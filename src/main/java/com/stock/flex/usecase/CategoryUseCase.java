package com.stock.flex.usecase;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.StockEntity;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.repository.CategoryRepository;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.CategoryRequest;
import com.stock.flex.resource.response.CategoryResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public ResponseEntity<?> createCategory(UUID stockId, CategoryRequest request, UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado! O usuário não está autenticado.");
        }

        Optional<StockEntity> stockOptional = stockRepository.findById(stockId);
        if (stockOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O estoque associado à categoria não foi encontrado.");
        }

        StockEntity stock = stockOptional.get();

        if (!stock.getUser().getId().equals(userSpringSecurity.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para criar uma categoria neste estoque.");
        }

        CategoryEntity newCategory = new CategoryEntity(request);
        newCategory.setStock(stock);

        CategoryEntity savedCategory = categoryRepository.save(newCategory);

        CategoryResponse response = new CategoryResponse(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<List<CategoryResponse>> getCategories(UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }

        List<CategoryEntity> categories = categoryRepository.findByStockUser(userSpringSecurity);

        List<CategoryResponse> categoryResponses = categories.stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(categoryResponses);
    }


    public ResponseEntity<?> getCategoryById(UUID id, UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado! O usuário não está autenticado.");
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }

        CategoryEntity category = categoryOptional.get();

        if (!category.getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para acessar esta categoria.");
        }

        return ResponseEntity.ok(new CategoryResponse(category));
    }

    @Transactional
    public ResponseEntity<?> updateCategory(UUID id, CategoryRequest request, UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado! O usuário não está autenticado.");
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }

        CategoryEntity category = categoryOptional.get();

        if (!category.getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para atualizar esta categoria.");
        }

        category.updateInfo(request);

        CategoryEntity updatedCategory = categoryRepository.save(category);

        CategoryResponse response = new CategoryResponse(updatedCategory);
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<?> deleteCategory(UUID id, UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado! O usuário não está autenticado.");
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }

        CategoryEntity category = categoryOptional.get();

        if (!category.getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para excluir esta categoria.");
        }

        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
