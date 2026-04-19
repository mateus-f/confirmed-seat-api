package me.mateusferreira.confirmed_seat_api.mapper;

import jakarta.validation.Valid;
import me.mateusferreira.confirmed_seat_api.model.dto.request.EventCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventCreateResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;

public class EventMapper {
    private EventMapper() {
    }

    public static Event toEntity(@Valid EventCreateRequest request) {
        return Event.builder()
                .title(request.title())
                .description(request.description())
                .location(request.location())
                .eventDate(request.eventDate())
                .capacity(request.capacity())
                .build();
    }

    public static EventCreateResponse toResponse(Event event) {
        return new EventCreateResponse(event.getId(), event.getTitle(), event.getEventDate(), event.getCapacity(), event.getStatus());
    }
}
