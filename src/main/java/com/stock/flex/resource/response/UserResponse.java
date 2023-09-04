package com.stock.flex.resource.response;

import com.stock.flex.entity.UserEntity;

import java.util.UUID;

public record UserResponse(UUID id, String usarname, String password) {
    public UserResponse(UserEntity user) {
        this(user.getId(),
      user.getUsername(),
      user.getPassword());
    }
}
