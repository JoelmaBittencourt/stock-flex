package com.stock.flex.resource;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.repository.CategoryRepository;
import com.stock.flex.resource.request.CategoryRequest;
import com.stock.flex.resource.response.CategoryResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    @Autowired
    CategoryRepository repository;

    @PostMapping
    public void create(@Valid @RequestBody CategoryRequest request){
        repository.save(new CategoryEntity(request));
    }

    @GetMapping
    public List<CategoryResponse> get() {
        return repository.findAll().stream().map(CategoryResponse::new).toList();
    }
    @PutMapping
    @Transactional
    public void update(@RequestBody CategoryResponse response) {
        var product = repository.getReferenceById(response.id());
        product.updateInfo(response);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
