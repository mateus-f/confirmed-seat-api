package me.mateusferreira.confirmed_seat_api.exception;

public class EventFinishedException extends ConflictException {
    public EventFinishedException(String message) {
        super(message);
    }
}
