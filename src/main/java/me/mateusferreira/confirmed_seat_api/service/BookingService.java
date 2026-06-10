package me.mateusferreira.confirmed_seat_api.service;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.exception.*;
import me.mateusferreira.confirmed_seat_api.mapper.BookingDetailMapper;
import me.mateusferreira.confirmed_seat_api.mapper.BookingSummaryMapper;
import me.mateusferreira.confirmed_seat_api.mapper.CreateBookingMapper;
import me.mateusferreira.confirmed_seat_api.model.dto.request.CreateBookingRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.BookingDetailResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.BookingSummaryResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.CreateBookingResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Booking;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;
import me.mateusferreira.confirmed_seat_api.model.entity.User;
import me.mateusferreira.confirmed_seat_api.model.enums.BookingStatus;
import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;
import me.mateusferreira.confirmed_seat_api.repository.BookingRepository;
import me.mateusferreira.confirmed_seat_api.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final EventService eventService;

    @Transactional
    public CreateBookingResponse create(CreateBookingRequest request) {
        Event event = eventRepository.findById(request.eventId())
                .orElseThrow(() -> new EventNotFoundException(request.eventId()));

        User user = authenticatedUserService.get();

        if (bookingRepository.existsByEventIdAndUserId(event.getId(), user.getId())) {
            throw new AlreadyRegisteredException(event.getTitle());
        }

        if (event.getStatus() == EventStatus.FINISHED || OffsetDateTime.now().isAfter(event.getEventDate())) {
            throw new EventFinishedException("Inscrições do evento encerrada!");
        }

        if (eventService.calculateAvailableSeats(event) == 0) {
            throw new EventCapacityExceededException(request.eventId());
        }

        Booking booking = CreateBookingMapper.toEntity(event, user);

        bookingRepository.save(booking);

        return CreateBookingMapper.toResponse(booking);
    }

    public Page<BookingSummaryResponse> findAllMyBookings(Pageable pageable) {
        User user = authenticatedUserService.get();

        Page<Booking> bookingPage = bookingRepository.findAllByUserIdAndStatusNot(user.getId(), BookingStatus.CANCELLED, pageable);

        return bookingPage.map(BookingSummaryMapper::toResponse);
    }

    public BookingDetailResponse getBookingByConfirmationCode(UUID confirmationCode) {
        User user = authenticatedUserService.get();

        Booking booking = bookingRepository.findByConfirmationCodeAndUserId(confirmationCode, user.getId())
                .orElseThrow(() -> new BookingNotFoundException("Inscrição não encontrada!"));

        return BookingDetailMapper.toResponse(booking);
    }
}
