package me.mateusferreira.confirmed_seat_api.model.dto.response;

public record UserLoginResponse(
        String token,
        long expiresIn,
        UserSummaryResponse user
) {
}
