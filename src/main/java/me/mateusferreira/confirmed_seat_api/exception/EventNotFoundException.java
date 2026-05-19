package me.mateusferreira.confirmed_seat_api.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long id) {
        super("Event com id " + id + " não encontrado.");
    }
}
