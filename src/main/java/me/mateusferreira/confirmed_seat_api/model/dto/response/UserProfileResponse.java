package me.mateusferreira.confirmed_seat_api.model.dto.response;

import me.mateusferreira.confirmed_seat_api.model.enums.UserRole;

import java.time.OffsetDateTime;

public record UserProfileResponse(
        Long id,
        String name,
        String email,
        String cpf,
        UserRole role,
        OffsetDateTime createdAt) {
}
