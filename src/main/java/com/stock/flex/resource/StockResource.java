package com.stock.flex.resource;

import com.stock.flex.entity.StockEntity;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.StockRequest;
import com.stock.flex.resource.response.StockResponse;
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
        var stock = repository.getById(id);
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