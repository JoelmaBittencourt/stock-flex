package com.stock.flex.resource;

import com.stock.flex.entity.Person;
import com.stock.flex.entity.StockEntity;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.StockRequest;
import com.stock.flex.resource.response.StockResponse;
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
import java.util.UUID;


@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/stock")
public class StockResource {

    // ...
    @Autowired
StockRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<StockResponse> create(
            @RequestBody StockRequest request,
            @AuthenticationPrincipal Person userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        StockEntity newStock = new StockEntity(request);
        newStock.setUser(userSpringSecurity);

        StockEntity savedStock = repository.save(newStock);

        StockResponse response = new StockResponse(savedStock);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<StockResponse>> get(
            @AuthenticationPrincipal Person userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        List<StockEntity> stocks = repository.findByUser(userSpringSecurity);

        List<StockResponse> stockResponses = stocks.stream().map(StockResponse::new).toList();

        return ResponseEntity.ok(stockResponses);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StockResponse> update(
            @PathVariable UUID id,
            @RequestBody StockRequest request,
            @AuthenticationPrincipal Person userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        StockEntity stock = repository.getById(id);

        if (!stock.getUser().equals(userSpringSecurity)) {
            throw new Exception("Acesso negado! Você não tem permissão para atualizar este estoque.");
        }

        // Realize a atualização apenas se o estoque pertencer ao usuário autenticado
        stock.updateInfo(request);

        return ResponseEntity.ok(new StockResponse(stock));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(
            @PathVariable UUID id,
            @AuthenticationPrincipal Person userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        StockEntity stock = repository.getById(id);

        if (!stock.getUser().equals(userSpringSecurity)) {
            throw new Exception("Acesso negado! Você não tem permissão para excluir este estoque.");
        }

        // Realize a exclusão apenas se o estoque pertencer ao usuário autenticado
        repository.deleteById(id);

        return ResponseEntity.ok().build();
    }


}
