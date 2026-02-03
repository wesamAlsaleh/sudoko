package com.avocado.sudoko.global.exceptions;

public class FileWriteException extends RuntimeException {
    public FileWriteException(String message) {
        super(message);
    }
}
