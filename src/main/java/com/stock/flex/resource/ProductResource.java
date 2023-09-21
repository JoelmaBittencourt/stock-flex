package com.stock.flex.resource;

import com.stock.flex.entity.UserEntity;
import com.stock.flex.resource.request.ProductRequest;
import com.stock.flex.resource.response.ProductResponse;
import com.stock.flex.useCase.ProductUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductUseCase productUseCase;

    @PostMapping("/{categoryId}")
    @Transactional
    @PreAuthorize("@productPermissionChecker.hasPermission(#categoryId, principal)")
    public ResponseEntity<?> create(
            @PathVariable UUID categoryId,
            @RequestBody ProductRequest request,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        return productUseCase.createProduct(categoryId, request, userSpringSecurity);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> get(@AuthenticationPrincipal UserEntity userSpringSecurity) throws Exception {
        return productUseCase.getProducts(userSpringSecurity);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        return productUseCase.getProductById(id, userSpringSecurity);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody ProductRequest request,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        return productUseCase.updateProduct(id, request, userSpringSecurity);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        productUseCase.deleteProduct(id, userSpringSecurity);
        return ResponseEntity.noContent().build();
    }
}
