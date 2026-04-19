package me.mateusferreira.confirmed_seat_api.service;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.mapper.EventMapper;
import me.mateusferreira.confirmed_seat_api.model.dto.request.EventCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventCreateResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;
import me.mateusferreira.confirmed_seat_api.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public EventCreateResponse create(EventCreateRequest request) {
        Event event = EventMapper.toEntity(request);

        eventRepository.save(event);

        return EventMapper.toResponse(event);
    }
}
