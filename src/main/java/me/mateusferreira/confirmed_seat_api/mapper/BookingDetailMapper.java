package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.response.BookingDetailResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Booking;

public class BookingDetailMapper {
    private BookingDetailMapper() {
    }

    public static BookingDetailResponse toResponse(Booking booking) {
        return new BookingDetailResponse(
                booking.getStatus(),
                booking.getConfirmationCode(),
                booking.getCreatedAt(),
                booking.getCancelledAt(),
                EventSummaryMapper.toResponse(booking.getEvent()),
                UserSummaryMapper.toResponse(booking.getUser())
        );
    }
}
