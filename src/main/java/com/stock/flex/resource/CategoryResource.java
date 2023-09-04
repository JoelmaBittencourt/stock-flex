package com.stock.flex.resource;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.StockEntity;
import com.stock.flex.repository.CategoryRepository;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.CategoryRequest;
import com.stock.flex.resource.response.CategoryResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    @Autowired
    CategoryRepository repository;

    @Autowired
    StockRepository stockRepository;


    @PostMapping("/{stockId}")
    @Transactional
    public ResponseEntity<?> create(
            @PathVariable UUID stockId,
            @RequestBody CategoryRequest request
    ) {
        // Verifique se o estoque associado à categoria existe
        Optional<StockEntity> stockOptional = stockRepository.findById(stockId);
        if (stockOptional.isEmpty()) {
            String errorMessage = "O estoque associado à categoria não foi encontrado.";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // Crie a categoria e associe-a ao estoque
        CategoryEntity newCategory = new CategoryEntity(request);
        newCategory.setStock(stockOptional.get());

        // Salve a categoria no banco de dados
        CategoryEntity savedCategory = repository.save(newCategory);

        // Retorne uma resposta de sucesso com a categoria criada
        CategoryResponse response = new CategoryResponse(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<CategoryResponse>> get() {
        var list = repository.findAll().stream().map(CategoryResponse::new).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
        var category = repository.getById(id);
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
