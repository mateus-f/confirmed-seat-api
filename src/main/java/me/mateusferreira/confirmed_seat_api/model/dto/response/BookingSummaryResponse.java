package me.mateusferreira.confirmed_seat_api.model.dto.response;

import me.mateusferreira.confirmed_seat_api.model.enums.BookingStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record BookingSummaryResponse(
        BookingStatus status,
        UUID confirmationCode,
        EventSummaryResponse event,
        OffsetDateTime createdAt
) {
}
