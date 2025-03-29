package com.kenzie.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class CustomEmptyStringExceptionTest {


    @SuppressWarnings("unchecked")
    public static CustomEmptyStringException getCustomException() {
        try {
            @SuppressWarnings("unchecked")
            Constructor constructor = CustomEmptyStringException.class.getConstructor(String.class);
            @SuppressWarnings("unchecked")
            CustomEmptyStringException exception = (CustomEmptyStringException) constructor.newInstance("test message");
            return exception;
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    @DisplayName("Test: Can CustomEmptyStringException be instantiated with one argument")
    @Test
    void canCreateCustomEmptyStringException() {

        try {
            @SuppressWarnings("unchecked")
            Constructor bookConstructor = CustomEmptyStringException.class.getConstructor(String.class);
            @SuppressWarnings("unchecked")

            CustomEmptyStringException exception = (CustomEmptyStringException) getCustomException();

        }
        catch(Exception e){
            fail("Missing or incorrectly defined constructor: " + e.getMessage());

        }
    }


}