//package com.stock.flex.resource;
//
//import com.stock.flex.entity.UserEntity;
//import com.stock.flex.repository.UserRepository;
//import com.stock.flex.resource.request.UserRequest;
//import com.stock.flex.resource.response.UserResponse;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/user")
//
//public class UserResource {
//
//    @Autowired
//    UserRepository repository;
//
//    @PostMapping
//    @Transactional
//    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
//        UserEntity saveduser = repository.save(new UserEntity(request));
//        UserResponse response = new UserResponse(saveduser);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UserResponse>> get() {
//        var user = repository.findAll().stream().map(UserResponse::new).toList();
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity getById(@PathVariable UUID id) {
//        var user = repository.getById(id);
//        return ResponseEntity.ok(new UserResponse(user));
//    }
//
//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<UserRequest> update(@PathVariable UUID id, @RequestBody UserRequest request) {
//        var user = repository.getReferenceById(id);
//        user.updateInfo(request);
//        return ResponseEntity.ok(new UserRequest(user));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity delete(@PathVariable UUID id) {
//        repository.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
//}
