package com.stock.flex.resource;

import com.stock.flex.entity.ProductEntity;
import com.stock.flex.repository.ProductRepository;
import com.stock.flex.resource.request.ProductRequest;
import com.stock.flex.resource.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        ProductEntity savedProduct = repository.save(new ProductEntity(request));
        ProductResponse response = new ProductResponse(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> get() {// se fosse todos os dados
        var product = repository.findAll().stream().map(ProductResponse::new).toList();
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductRequest> update(@PathVariable UUID id, @RequestBody ProductRequest request) {
        var product = repository.getById(id);
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
