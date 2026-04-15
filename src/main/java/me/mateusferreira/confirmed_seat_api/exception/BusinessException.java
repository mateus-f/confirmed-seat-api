package me.mateusferreira.confirmed_seat_api.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
