package com.kenzie.customexceptions;

/**
 * Custom exception for when we try to create a new user but one with
 * duplicate fields already exists.
 */
public class UserExistsException extends Exception {


    private static final long serialVersionUID = 735157762732994502L;

    public UserExistsException() {
        super();
    }

    public UserExistsException(String message) {
        super(message);
    }

    public UserExistsException(String message, Throwable e) {
        super(message, e);
    }

    public UserExistsException(Throwable e) {
        super(e);
    }
}
