package com.stock.flex.resource;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.entity.StockEntity;
import com.stock.flex.repository.CategoryRepository;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.CategoryRequest;
import com.stock.flex.resource.response.CategoryResponse;
import com.stock.flex.useCase.CategoryUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/category")
public class CategoryResource {

    @Autowired
    private CategoryUseCase categoryUseCase;

    @PostMapping("/{stockId}")
    @Transactional
    @PreAuthorize("@categoryPermissionChecker.hasPermission(#stockId, principal)")
    public ResponseEntity<?> create(
            @PathVariable UUID stockId,
            @RequestBody CategoryRequest request,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        return categoryUseCase.createCategory(stockId, request, userSpringSecurity);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> get(@AuthenticationPrincipal UserEntity userSpringSecurity) throws Exception {
        return categoryUseCase.getCategories (userSpringSecurity);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        return categoryUseCase.getCategoryById(id, userSpringSecurity);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody CategoryRequest request,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        return categoryUseCase.updateCategory(id, request, userSpringSecurity);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        return categoryUseCase.deleteCategory(id, userSpringSecurity);
    }
}
