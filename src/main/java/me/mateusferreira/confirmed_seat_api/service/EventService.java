package me.mateusferreira.confirmed_seat_api.service;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.exception.EventNotFoundException;
import me.mateusferreira.confirmed_seat_api.mapper.EventCreateMapper;
import me.mateusferreira.confirmed_seat_api.mapper.EventDetailMapper;
import me.mateusferreira.confirmed_seat_api.mapper.EventSummaryMapper;
import me.mateusferreira.confirmed_seat_api.model.dto.request.EventCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.request.EventUpdateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventCreateResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventDetailResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventSummaryResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;
import me.mateusferreira.confirmed_seat_api.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static me.mateusferreira.confirmed_seat_api.model.enums.BookingStatus.CONFIRMED;
import static me.mateusferreira.confirmed_seat_api.model.enums.EventStatus.OPEN_FOR_BOOKING;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public EventCreateResponse create(EventCreateRequest request) {
        Event event = EventCreateMapper.toEntity(request);

        eventRepository.save(event);

        return EventCreateMapper.toResponse(event);
    }

    public Page<EventSummaryResponse> findAllAvailable(Pageable pageable) {
        Page<Event> eventPage = eventRepository.findByIsActiveTrueAndStatus(OPEN_FOR_BOOKING, pageable);

        return eventPage.map(EventSummaryMapper::toResponse);
    }

    public EventDetailResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        return EventDetailMapper.toResponse(event, calculateReservedSeats(event), calculateAvailableSeats(event));
    }

    private Integer calculateReservedSeats(Event event) {
        return (int) event.getBookings().stream()
                .filter(booking -> booking.getStatus().equals(CONFIRMED))
                .count();
    }

    public Integer calculateAvailableSeats(Event event) {
        return event.getCapacity() - calculateReservedSeats(event);
    }

    @Transactional
    public EventDetailResponse update(Long id, EventUpdateRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        if (request.title() != null) event.setTitle(request.title());
        if (request.description() != null) event.setDescription(request.description());
        if (request.location() != null) event.setLocation(request.location());
        if (request.eventDate() != null) event.setEventDate(request.eventDate());
        if (request.status() != null) event.setStatus(request.status());

        eventRepository.save(event);

        return EventDetailMapper.toResponse(event, calculateReservedSeats(event), calculateAvailableSeats(event));
    }

    public ResponseEntity<Void> delete(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        event.setIsActive(false);

        eventRepository.save(event);

        return ResponseEntity.noContent().build();
    }
}
