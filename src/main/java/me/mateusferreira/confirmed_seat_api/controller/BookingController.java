package me.mateusferreira.confirmed_seat_api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.request.CreateBookingRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.BookingDetailResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.BookingSummaryResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.CreateBookingResponse;
import me.mateusferreira.confirmed_seat_api.service.BookingService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
@SecurityRequirement(name = "bearerAuth")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public CreateBookingResponse createBooking(@Valid @RequestBody CreateBookingRequest request) {
        return bookingService.create(request);
    }

    @GetMapping("/me")
    public ResponseEntity<Page<BookingSummaryResponse>> listMyBookings(
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        Page<BookingSummaryResponse> bookings = bookingService.findAllMyBookings(pageable);

        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{confirmationCode}")
    public BookingDetailResponse getBookingByConfirmationCode(@PathVariable UUID confirmationCode) {
        return bookingService.getBookingByConfirmationCode(confirmationCode);
    }

}
