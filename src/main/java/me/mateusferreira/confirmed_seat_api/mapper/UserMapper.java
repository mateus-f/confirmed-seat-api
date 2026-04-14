package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.request.UserCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserCreatedResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.User;

public class UserMapper {

    private UserMapper() {
        /* This utility class should not be instantiated */
    }

    public static User toEntity(UserCreateRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .cpf(request.cpf())
                .password(request.password())
                .role(request.role())
                .build();
    }

    public static UserCreatedResponse toResponse(User user) {
        return new UserCreatedResponse(user.getId(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }
}
