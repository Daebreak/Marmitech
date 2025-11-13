package com.marmitech.Marmitech.Exceptions;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message) {
        super( message );
    }
}