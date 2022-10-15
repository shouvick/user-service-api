package com.shouvick.userservice.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {

        super(String.format("User with Id %s not found", id));
    }
}