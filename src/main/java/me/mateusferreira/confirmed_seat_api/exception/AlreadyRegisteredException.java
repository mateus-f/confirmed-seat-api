package me.mateusferreira.confirmed_seat_api.exception;

public class AlreadyRegisteredException extends ConflictException {
    public AlreadyRegisteredException(String eventTitle) {
        super("Usuário já inscrito em " + eventTitle + ".");
    }
}
