package com.stock.flex.resource.request;

import com.stock.flex.entity.UserEntity;

public record UserRequest(
        String username,
        String password

) {
    public UserRequest(UserEntity user) {
        this(user.getUsername(), user.getPassword());
    }
}
