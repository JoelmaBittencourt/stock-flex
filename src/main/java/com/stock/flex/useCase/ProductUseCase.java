package com.stock.flex.useCase;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.ProductEntity;
import com.stock.flex.entity.StockEntity;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.repository.ProductRepository;
import com.stock.flex.repository.CategoryRepository;
import com.stock.flex.resource.request.ProductRequest;
import com.stock.flex.resource.response.ProductResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public ResponseEntity<?> createProduct(UUID categoryId, ProductRequest request, UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado! O usuário não está autenticado.");
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A categoria associada ao produto não foi encontrada.");
        }

        CategoryEntity category = categoryOptional.get();
        StockEntity stock = category.getStock();

        if (!stock.getUser().getId().equals(userSpringSecurity.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para criar um produto nesta categoria.");
        }

        ProductEntity newProduct = new ProductEntity(request);
        newProduct.setCategory(category);

        ProductEntity savedProduct = productRepository.save(newProduct);

        ProductResponse response = new ProductResponse(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<List<ProductResponse>> getProducts(UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }

        List<ProductEntity> products = productRepository.findByCategoryStockUser(userSpringSecurity);

        List<ProductResponse> productResponses = products.stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productResponses);
    }

    public ResponseEntity<?> getProductById(UUID id, UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado! O usuário não está autenticado.");
        }

        Optional<ProductEntity> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        ProductEntity product = productOptional.get();

        if (!product.getCategory().getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para acessar este produto.");
        }

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @Transactional
    public ResponseEntity<?> updateProduct(UUID id, ProductRequest request, UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado! O usuário não está autenticado.");
        }

        Optional<ProductEntity> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        ProductEntity product = productOptional.get();

        if (!product.getCategory().getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para atualizar este produto.");
        }

        product.updateInfo(request);

        ProductEntity updatedProduct = productRepository.save(product);

        ProductResponse response = new ProductResponse(updatedProduct);
        return ResponseEntity.ok(response);
    }

    @Transactional
    public void deleteProduct(UUID id, UserEntity userSpringSecurity) {
        if (Objects.isNull(userSpringSecurity)) {
            throw new RuntimeException("Acesso negado! O usuário não está autenticado.");
        }

        Optional<ProductEntity> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Produto não encontrado.");
        }

        ProductEntity product = productOptional.get();

        if (!product.getCategory().getStock().getUser().equals(userSpringSecurity)) {
            throw new RuntimeException("Acesso negado! Você não tem permissão para excluir este produto.");
        }

        productRepository.deleteById(id);
    }
}
