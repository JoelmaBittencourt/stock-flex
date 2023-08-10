package com.stock.flex.resource;

import com.stock.flex.entity.ProductEntity;
import com.stock.flex.repository.ProductRepository;
import com.stock.flex.resource.request.ProductRequest;
import com.stock.flex.resource.response.ProductResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    ProductRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody ProductRequest request) {
        System.out.println(request);
        repository.save(new ProductEntity(request));
    }

    @GetMapping
    public List<ProductResponse> get() {// se fosse todos os dados
        return repository.findAll().stream().map(ProductResponse::new).toList();
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody ProductResponse response) {
        var product = repository.getReferenceById(response.id());
        product.updateInfo(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
