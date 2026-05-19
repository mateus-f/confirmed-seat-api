package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.response.EventDetailResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;

public class EventDetailMapper {
    private EventDetailMapper() {
    }

    public static EventDetailResponse toResponse(Event event, Integer reservedSeats, Integer availableSeats) {
        return new EventDetailResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getEventDate(),
                event.getCapacity(),
                reservedSeats,
                availableSeats,
                event.getStatus());
    }
}
