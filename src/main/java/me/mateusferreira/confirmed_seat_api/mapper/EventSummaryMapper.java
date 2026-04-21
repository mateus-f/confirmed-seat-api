package me.mateusferreira.confirmed_seat_api.mapper;

import me.mateusferreira.confirmed_seat_api.model.dto.response.EventSummaryResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;

public class EventSummaryMapper {
    private EventSummaryMapper() {
    }

    public static EventSummaryResponse toResponse(Event event, Integer availableSeats) {
        return new EventSummaryResponse(event.getId(), event.getTitle(), event.getLocation(), event.getEventDate(), event.getCapacity(), availableSeats, event.getStatus());
    }
}
