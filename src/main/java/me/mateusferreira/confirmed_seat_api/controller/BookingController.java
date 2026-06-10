package me.mateusferreira.confirmed_seat_api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.request.CreateBookingRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.CreateBookingResponse;
import me.mateusferreira.confirmed_seat_api.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
