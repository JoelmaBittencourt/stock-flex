package com.stock.flex.resource;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.PersonEntity;
import com.stock.flex.entity.ProductEntity;
import com.stock.flex.repository.CategoryRepository;
import com.stock.flex.repository.ProductRepository;
import com.stock.flex.resource.request.ProductRequest;
import com.stock.flex.resource.response.ProductResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/{categoryId}")
    @Transactional
    public ResponseEntity<?> create(
            @PathVariable UUID categoryId,
            @RequestBody ProductRequest request,
            @AuthenticationPrincipal PersonEntity userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A categoria associada ao produto não foi encontrada.");
        }

        CategoryEntity category = categoryOptional.get();
        if (!category.getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para criar um produto nesta categoria.");
        }

        ProductEntity newProduct = new ProductEntity(request);
        newProduct.setCategory(category);

        ProductEntity savedProduct = repository.save(newProduct);

        ProductResponse response = new ProductResponse(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> get(@AuthenticationPrincipal PersonEntity userSpringSecurity) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        List<ProductEntity> products = repository.findByCategoryStockUser(userSpringSecurity);

        List<ProductResponse> productResponses = products.stream().map(ProductResponse::new).toList();

        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal PersonEntity userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        Optional<ProductEntity> productOptional = repository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        ProductEntity product = productOptional.get();

        if (!product.getCategory().getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para acessar este produto.");
        }

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody ProductRequest request,
            @AuthenticationPrincipal PersonEntity userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        Optional<ProductEntity> productOptional = repository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        ProductEntity product = productOptional.get();

        if (!product.getCategory().getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para atualizar este produto.");
        }

        product.updateInfo(request);

        ProductRequest updatedProductRequest = new ProductRequest(product);
        return ResponseEntity.ok(updatedProductRequest);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(
            @PathVariable UUID id,
            @AuthenticationPrincipal PersonEntity userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        Optional<ProductEntity> productOptional = repository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        ProductEntity product = productOptional.get();

        if (!product.getCategory().getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para excluir este produto.");
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
