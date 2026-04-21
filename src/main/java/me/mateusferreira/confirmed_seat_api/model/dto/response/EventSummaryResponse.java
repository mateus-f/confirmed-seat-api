package me.mateusferreira.confirmed_seat_api.model.dto.response;

import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;

import java.time.OffsetDateTime;

public record EventSummaryResponse(
        Long id,
        String title,
        String location,
        OffsetDateTime eventDate,
        Integer capacity,
        Integer availableSeats,
        EventStatus status
) {
}
