package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.request.UserCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserCreateResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.User;

public class UserCreateMapper {

    private UserCreateMapper() {
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

    public static UserCreateResponse toResponse(User user) {
        return new UserCreateResponse(user.getId(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }
}
