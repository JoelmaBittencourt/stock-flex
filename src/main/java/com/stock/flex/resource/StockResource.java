package com.stock.flex.resource;

import com.stock.flex.entity.StockEntity;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.resource.request.StockRequest;
import com.stock.flex.resource.response.StockResponse;
import com.stock.flex.usecase.StockUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/stock")
public class StockResource {

    @Autowired
    private StockUseCase useCase;

    @PostMapping
    @Transactional
    public ResponseEntity<StockResponse> createStock(
            @RequestBody StockRequest request,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) {
        StockEntity savedStock = useCase.createStock(request, userSpringSecurity);
        StockResponse response = new StockResponse(savedStock);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<StockResponse> getStockById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
            StockEntity stock = useCase.getStockById(id, userSpringSecurity);
            return ResponseEntity.ok(new StockResponse(stock));
        }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<StockResponse>> getAllStocks(
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) {
        List<StockEntity> stocks = useCase.getAllStocks(userSpringSecurity);
        List<StockResponse> stockResponses = stocks.stream().map(StockResponse::new).toList();
        return ResponseEntity.ok(stockResponses);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StockResponse> updateStock(
            @PathVariable UUID id,
            @RequestBody StockRequest request,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) throws Exception {
        StockEntity updatedStock = useCase.updateStock(id, request, userSpringSecurity);
        return ResponseEntity.ok(new StockResponse(updatedStock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStock(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserEntity userSpringSecurity
    ) {
        try {
            useCase.deleteStock(id, userSpringSecurity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
