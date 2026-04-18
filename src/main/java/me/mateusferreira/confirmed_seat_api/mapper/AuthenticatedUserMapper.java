package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.response.UserProfileResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.User;

public class AuthenticatedUserMapper {
    private AuthenticatedUserMapper() {
    }

    public static UserProfileResponse toResponse(User user) {
        return new UserProfileResponse(user.getId(), user.getName(), user.getEmail(), user.getCpf(), user.getRole(), user.getCreatedAt());
    }
}
