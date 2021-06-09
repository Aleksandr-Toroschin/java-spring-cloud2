package ru.toroschin.spring.error_handling;

public class UserEnableException extends RuntimeException{
    public UserEnableException(String message) {
        super(message);
    }
}
