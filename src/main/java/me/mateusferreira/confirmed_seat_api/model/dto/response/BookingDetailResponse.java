package me.mateusferreira.confirmed_seat_api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.mateusferreira.confirmed_seat_api.model.enums.BookingStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record BookingDetailResponse(
        BookingStatus status,
        UUID confirmationCode,
        OffsetDateTime createdAt,
        OffsetDateTime cancelledAt,
        EventSummaryResponse event,
        UserSummaryResponse user
) {
}
