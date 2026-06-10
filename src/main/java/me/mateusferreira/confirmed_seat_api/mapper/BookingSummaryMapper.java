package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.response.BookingSummaryResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Booking;

public class BookingSummaryMapper {
    private BookingSummaryMapper() {
    }

    public static BookingSummaryResponse toResponse(Booking booking) {
        return new BookingSummaryResponse(booking.getStatus(), booking.getConfirmationCode(), EventSummaryMapper.toResponse(booking.getEvent()), booking.getCreatedAt());
    }
}
