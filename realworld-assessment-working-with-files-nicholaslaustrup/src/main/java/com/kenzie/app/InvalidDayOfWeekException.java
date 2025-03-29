package com.kenzie.app;

public class InvalidDayOfWeekException extends IllegalArgumentException{
    public InvalidDayOfWeekException(String message, Throwable cause) {
        super(message, cause);
    }
}
