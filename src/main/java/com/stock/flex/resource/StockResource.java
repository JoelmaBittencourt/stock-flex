package com.stock.flex.resource;

import com.stock.flex.entity.StockEntity;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.StockRequest;
import com.stock.flex.resource.response.StockResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stock")
public class StockResource {

    @Autowired
    StockRepository repository;

    @PostMapping
    public void create(@Valid @RequestBody StockRequest request){
        repository.save(new StockEntity(request));
    }

    @GetMapping
    public List<StockResponse> get() {
        return repository.findAll().stream().map(StockResponse::new).toList();
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody StockResponse response) {
        var product = repository.getReferenceById(response.id());
        product.updateInfo(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
