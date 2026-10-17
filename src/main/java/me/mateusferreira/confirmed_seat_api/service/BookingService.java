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
import me.mateusferreira.confirmed_seat_api.model.dto.response.PageResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.Booking;
import me.mateusferreira.confirmed_seat_api.model.entity.Event;
import me.mateusferreira.confirmed_seat_api.model.entity.User;
import me.mateusferreira.confirmed_seat_api.model.enums.BookingStatus;
import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;
import me.mateusferreira.confirmed_seat_api.model.enums.UserRole;
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
            throw new EventFinishedException("Reservas do evento encerradas!");
        }

        if (eventService.calculateAvailableSeats(event) == 0) {
            throw new EventCapacityExceededException(request.eventId());
        }

        Booking booking = CreateBookingMapper.toEntity(event, user);

        bookingRepository.save(booking);

        return CreateBookingMapper.toResponse(booking);
    }

    public PageResponse<BookingSummaryResponse> findAllMyBookings(Pageable pageable) {
        User user = authenticatedUserService.get();

        Page<Booking> bookingPage = bookingRepository.findAllByUserIdAndStatusNot(user.getId(), BookingStatus.CANCELLED, pageable);
        Page<BookingSummaryResponse> responsePage = bookingPage.map(BookingSummaryMapper::toResponse);

        return PageResponse.from(responsePage);
    }

    private Booking findBooking(UUID confirmationCode) {
        User user = authenticatedUserService.get();

        if (user.getRole() == UserRole.ADMIN) {
            return bookingRepository.findByConfirmationCode(confirmationCode)
                    .orElseThrow(() -> new BookingNotFoundException("Reserva não encontrada!"));
        }

        return bookingRepository.findByConfirmationCodeAndUserId(confirmationCode, user.getId())
                .orElseThrow(() -> new BookingNotFoundException("Reserva não encontrada!"));
    }

    public BookingDetailResponse getBookingByConfirmationCode(UUID confirmationCode) {
        return BookingDetailMapper.toResponse(findBooking(confirmationCode));
    }

    @Transactional
    public BookingDetailResponse cancelBookingByConfirmationCode(UUID confirmationCode) {
        Booking booking = findBooking(confirmationCode);

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BusinessException("Reserva em evento já se encontra cancelada!");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking.setCancelledAt(OffsetDateTime.now());

        bookingRepository.save(booking);

        return BookingDetailMapper.toResponse(booking);
    }
}
