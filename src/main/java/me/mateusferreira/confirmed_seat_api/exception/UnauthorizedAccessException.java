package me.mateusferreira.confirmed_seat_api.exception;

public class UnauthorizedAccessException extends BusinessException {
    public UnauthorizedAccessException() {
        super("Usuário não autenticado ou sessão expirou.");
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
