package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.response.UserSummaryResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.User;

public class UserSummaryMapper {

    private UserSummaryMapper() {
    }

    public static UserSummaryResponse toResponse(User user) {
        return new UserSummaryResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
