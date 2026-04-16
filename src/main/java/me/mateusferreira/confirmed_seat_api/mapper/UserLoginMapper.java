package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.response.UserLoginResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserSummaryResponse;

public class UserLoginMapper {

    private UserLoginMapper() {
    }

    public static UserLoginResponse toResponse(String jwtToken, long expiresIn, UserSummaryResponse user) {
        return new UserLoginResponse(jwtToken, expiresIn, user);
    }
}
