package com.stock.flex.resource;

import com.stock.flex.entity.StockEntity;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.ProductRequest;
import com.stock.flex.resource.request.StockRequest;
import com.stock.flex.resource.response.ProductResponse;
import com.stock.flex.resource.response.StockResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stock")
public class StockResource {

    @Autowired
    StockRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<StockResponse> create(@RequestBody StockRequest request) {
        StockEntity savedStock = repository.save(new StockEntity(request));
        StockResponse response = new StockResponse(savedStock);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StockResponse>> get() {// se fosse todos os dados
        var stock = repository.findAll().stream().map(StockResponse::new).toList();
        return ResponseEntity.ok(stock);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StockResponse> update(@PathVariable UUID id, @RequestBody StockRequest request) {
        var stock = repository.getReferenceById(id);
        stock.updateInfo(request);
        return ResponseEntity.ok(new StockResponse(stock));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}