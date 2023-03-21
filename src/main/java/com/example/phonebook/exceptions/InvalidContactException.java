package com.example.phonebook.exceptions;

public class InvalidContactException extends RuntimeException{
    public InvalidContactException(String message) {
        super(message);
    }
}
