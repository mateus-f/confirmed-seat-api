package me.mateusferreira.confirmed_seat_api.exception;

public class BookingNotFoundException extends NotFoundException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
