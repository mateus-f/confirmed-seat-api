package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.response.CreateBookingResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Booking;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;
import me.mateusferreira.confirmed_seat_api.model.entity.User;

public class CreateBookingMapper {
    private CreateBookingMapper() {
    }

    public static Booking toEntity(Event event, User user) {
        return Booking.builder()
                .event(event)
                .user(user)
                .build();
    }

    public static CreateBookingResponse toResponse(Booking booking) {
        return new CreateBookingResponse(
                booking.getConfirmationCode(),
                booking.getId(),
                booking.getEvent().getTitle(),
                booking.getEvent().getEventDate(),
                booking.getUser().getName(),
                booking.getStatus(),
                booking.getCreatedAt());
    }
}
