package com.stock.flex.resource;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.repository.CategoryRepository;
import com.stock.flex.resource.request.CategoryRequest;
import com.stock.flex.resource.response.CategoryResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    @Autowired
    CategoryRepository repository;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CategoryRequest request, UriComponentsBuilder uriBuilder) {
        var cqategoryEntity = new CategoryEntity(request);
        repository.save(cqategoryEntity);
        var uri = uriBuilder.path("/category/{id}").buildAndExpand(cqategoryEntity.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryResponse(cqategoryEntity));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> get() {
        var list = repository.findAll().stream().map(CategoryResponse::new).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable UUID id) {
        var category = repository.getReferenceById(id);
        return ResponseEntity.ok(new CategoryResponse(category));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoryRequest> updateCategory(@PathVariable UUID id, @RequestBody CategoryRequest request) {
        var category = repository.getReferenceById(id);
        category.updateInfo(request);
        return ResponseEntity.ok(new CategoryRequest(category));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
