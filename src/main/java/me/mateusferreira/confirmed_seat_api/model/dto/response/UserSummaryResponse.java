package me.mateusferreira.confirmed_seat_api.model.dto.response;

import me.mateusferreira.confirmed_seat_api.model.enums.UserRole;

public record UserSummaryResponse(
        Long id,
        String name,
        String email,
        UserRole role
) {
}
