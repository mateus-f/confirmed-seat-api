package me.mateusferreira.confirmed_seat_api.exception;

public class EventCapacityExceededException extends ConflictException {
    public EventCapacityExceededException(Long eventId) {
        super("O evento com ID " + eventId + " não possui mais vagas disponíveis.");
    }
}
