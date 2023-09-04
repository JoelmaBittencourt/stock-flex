package com.stock.flex.resource;

import com.stock.flex.entity.CategoryEntity;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            @RequestBody ProductRequest request
    ) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            String errorMessage = "A categoria associada ao produto não foi encontrada.";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        ProductEntity newProduct = new ProductEntity(request);
        newProduct.setCategory(categoryOptional.get());

        ProductEntity savedProduct = repository.save(newProduct);

        ProductResponse response = new ProductResponse(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> get() {
        var product = repository.findAll().stream().map(ProductResponse::new).toList();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
        var product = repository.getById(id);
        return ResponseEntity.ok(new ProductResponse(product));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductRequest> update(@PathVariable UUID id, @RequestBody ProductRequest request) {
        var product = repository.getReferenceById(id);
        product.updateInfo(request);
        return ResponseEntity.ok(new ProductRequest(product));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
