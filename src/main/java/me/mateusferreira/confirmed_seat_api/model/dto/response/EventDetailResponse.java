package me.mateusferreira.confirmed_seat_api.model.dto.response;

import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;

import java.time.OffsetDateTime;

public record EventDetailResponse(
        Long id,
        String title,
        String description,
        String location,
        OffsetDateTime eventDate,
        Integer capacity,
        Integer reservedSeats,
        Integer availableSeats,
        EventStatus status
) {
}
