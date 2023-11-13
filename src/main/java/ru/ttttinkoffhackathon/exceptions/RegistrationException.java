package ru.ttttinkoffhackathon.exceptions;

public class RegistrationException extends RuntimeException {
    public RegistrationException() {
        super("Ошибка регистрации бота в сессии");
    }
}
