package me.mateusferreira.confirmed_seat_api.service;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.mapper.EventCreateMapper;
import me.mateusferreira.confirmed_seat_api.mapper.EventSummaryMapper;
import me.mateusferreira.confirmed_seat_api.model.dto.request.EventCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventCreateResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventSummaryResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;
import me.mateusferreira.confirmed_seat_api.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        return eventPage.map(event -> EventSummaryMapper.toResponse(event, calculateAvailableSeats(event)));
    }

    private Integer calculateAvailableSeats(Event event) {
        long confirmedSeats = event.getBookings().stream()
                .filter(booking -> booking.getStatus().equals(CONFIRMED))
                .count();

        return event.getCapacity() - (int) confirmedSeats;
    }
}
