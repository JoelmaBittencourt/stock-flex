package com.stock.flex.resource;

import com.stock.flex.entity.CategoryEntity;
import com.stock.flex.entity.Person;
import com.stock.flex.entity.StockEntity;
import com.stock.flex.repository.CategoryRepository;
import com.stock.flex.repository.StockRepository;
import com.stock.flex.resource.request.CategoryRequest;
import com.stock.flex.resource.response.CategoryResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearer-key")
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
            @RequestBody CategoryRequest request,
            @AuthenticationPrincipal Person userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        // Verifique se o estoque associado à categoria existe
        Optional<StockEntity> stockOptional = stockRepository.findById(stockId);
        if (stockOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O estoque associado à categoria não foi encontrado.");
        }

        StockEntity stock = stockOptional.get();
        if (!stock.getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para criar uma categoria neste estoque.");
        }

        // Crie a categoria e associe-a ao estoque
        CategoryEntity newCategory = new CategoryEntity(request);
        newCategory.setStock(stock);

        // Salve a categoria no banco de dados
        CategoryEntity savedCategory = repository.save(newCategory);

        // Retorne uma resposta de sucesso com a categoria criada
        CategoryResponse response = new CategoryResponse(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> get(@AuthenticationPrincipal Person userSpringSecurity) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        // Consulta todas as categorias associadas ao usuário autenticado
        List<CategoryEntity> categories = repository.findByStockUser(userSpringSecurity);

        List<CategoryResponse> categoryResponses = categories.stream().map(CategoryResponse::new).toList();

        return ResponseEntity.ok(categoryResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal Person userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        // Consulte a categoria pelo ID
        Optional<CategoryEntity> categoryOptional = repository.findById(id);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }

        CategoryEntity category = categoryOptional.get();

        // Verifique se o usuário tem permissão para acessar esta categoria
        if (!category.getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para acessar esta categoria.");
        }

        return ResponseEntity.ok(new CategoryResponse(category));
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<CategoryRequest> updateCategory(
//            @PathVariable UUID id,
//            @RequestBody CategoryRequest request,
//            @AuthenticationPrincipal Person userSpringSecurity
//    ) throws Exception {
//        if (Objects.isNull(userSpringSecurity)) {
//            throw new Exception("Acesso negado! O usuário não está autenticado.");
//        }
//
//        // Consulte a categoria pelo ID
//        Optional<CategoryEntity> categoryOptional = repository.findById(id);
//        if (categoryOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
//        }
//
//        CategoryEntity category = categoryOptional.get();
//
//        // Verifique se o usuário tem permissão para atualizar esta categoria
//        if (!category.getStock().getUser().equals(userSpringSecurity)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para atualizar esta categoria.");
//        }
//
//        category.updateInfo(request);
//        return ResponseEntity.ok(new CategoryRequest(category));
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(
            @PathVariable UUID id,
            @AuthenticationPrincipal Person userSpringSecurity
    ) throws Exception {
        if (Objects.isNull(userSpringSecurity)) {
            throw new Exception("Acesso negado! O usuário não está autenticado.");
        }

        // Consulte a categoria pelo ID
        Optional<CategoryEntity> categoryOptional = repository.findById(id);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }

        CategoryEntity category = categoryOptional.get();

        // Verifique se o usuário tem permissão para excluir esta categoria
        if (!category.getStock().getUser().equals(userSpringSecurity)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Você não tem permissão para excluir esta categoria.");
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
